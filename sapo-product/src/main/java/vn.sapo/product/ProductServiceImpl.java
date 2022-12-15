package vn.sapo.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.sapo.brand.*;
import vn.sapo.category.*;
import vn.sapo.entities.product.*;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.entities.tax.TaxType;
import vn.sapo.item.*;
import vn.sapo.item.dto.ItemResult;
import vn.sapo.media.*;
import vn.sapo.media.dto.MediaResult;
import vn.sapo.product.dto.*;
import vn.sapo.product_tax.*;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.TaxService;
import vn.sapo.tax.dto.TaxMapper;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    TaxMapper taxMapper;

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTaxRepository productTaxRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    MediaService mediaService;

    @Autowired
    TaxService taxService;

    @Autowired
    ProductTaxService productTaxService;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResult> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    Integer productId = product.getId();
                    ProductResult dto = productMapper.toDTO(product);
                    List<ProductTax> productTaxSaleList = productTaxRepository.findAllByProductIdAndTaxType(productId, TaxType.TAX_SALE);
                    List<ProductTax> productTaxPurchaseList = productTaxRepository.findAllByProductIdAndTaxType(productId, TaxType.TAX_PURCHASE);
                    dto.setTotalInventory(itemService.getTotalInventoryQuantityByProductId(productId));
                    dto.setAvailableInventory(itemService.getAvailableInventoryQuantityByProductId(productId));
                    return dto;
                })
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public ProductResult findById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productMapper.toDTO(productOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public ProductDetailResult findDetailById(Integer id) {
        Product product = productRepository.findById(id).get();
        ProductDetailResult productDetailResult = productMapper.toDTODetail(product);

        productDetailResult.setCategory(categoryMapper.toDTO(categoryRepository.findById(product.getCategoryId()).get()));
        productDetailResult.setBrand(brandMapper.toDTO(brandRepository.findById(product.getBrandId()).get()));
        productDetailResult.setMediaResults(mediaService.findAllById(product.getId()));
        productDetailResult.setItemResult(itemService.findAllByProductId(product.getId()));
        List<ProductTaxResult> productTaxResults = productTaxService.findAllByProductId(product.getId());
        productDetailResult.setTaxResults(taxService.findAllByProductId(productTaxResults));
        return productDetailResult;
    }

    @Override
    public List<ProductResult> findAllProductByDeleted(boolean deleted) {
        return null;
    }

    @Override
    public ProductResult findByIdProduct(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public ProductResult create(CreateProductParam createProductParam) {
        try {
            Product product = productMapper.toModel(createProductParam);
            product.setStatus(
                    createProductParam.getEnableSell() == true ? ProductStatus.AVAILABLE :
                            ProductStatus.UNAVAILABLE);

            Product productSaved = productRepository.save(product);
            ProductResult dto = productMapper.toDTO(productSaved);
            ItemResult itemResult = itemService.create(itemMapper.toDTO(createProductParam, dto));
            List<MediaResult> mediaResults = mediaService.save(createProductParam.getMediaList(), productSaved);
            List<ProductTaxResult> productTaxResult = productTaxService.create(createProductParam.getTaxList(), productSaved);
            return dto;
        } catch (Exception e) {
            return null;
        }

    }


    @Override
    @Transactional
    public ProductResult createShortProduct(ProductShortParam productShortParam) {
        Product product = productMapper.toModel(productShortParam);
        product.setStatus(ProductStatus.AVAILABLE);
        product.setWholesalePrice(new BigDecimal(Integer.parseInt(productShortParam.getRetailPrice())));
        product.setBrandId(1);
        product.setApplyTax(false);


        Product p = productRepository.save(product);

        Item item = new Item();
        item.setProductId(p.getId());
        item.setQuantity(Integer.parseInt(productShortParam.getQuantity()));
        item.setAvailable(Integer.parseInt(productShortParam.getQuantity()));
        item.setPrice(new BigDecimal(Integer.parseInt(productShortParam.getImportPrice())));

        return productMapper.toDTO(product);
    }


    @Override
    public Product createProduct(ProductCreate productCreate) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getAllProductItemPage(Integer pageNo, Integer pageSize, String title, Integer categoryId, Integer brandId, String status) {
        pageNo = pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        if (products.hasContent()) {
            if (title.equals("-1") && categoryId == -1 && brandId == -1 && status.equals("-1")) {
                products = productRepository.findAll(pageable);
            } else if (categoryId == -1 && brandId == -1 && status.equals("-1")) {
                products = productRepository.findAllByTitleContaining(title, pageable);
            } else if (brandId == -1 && status.equals("-1")) {
                if (title.equals("-1")) {
                    title = "";
                }
                products = productRepository.findAllByTitleContainingAndCategoryId(categoryId, title, pageable);
            } else if (status.equals("-1")) {
                if (title.equals("-1")) {
                    title = "";
                }
                products = productRepository.findAllByTitleContainingAndBrandId(brandId, title, pageable);
            } else {
                if (title.equals("-1")) {
                    title = "";
                }
                products = productRepository.findAllByTitleContainingAndStatus(ProductStatus.parseProductStatus(status), title, pageable);
            }

            List<ProductItemResult> dtoList = products.getContent().stream().map(product -> {
                ProductItemResult dto = productMapper.toDTOPage(product);
                dto.setImage(mediaService.getLinkMediaByProductIdIsMain(product.getId()));
                dto.setInventory(itemService.getTotalInventoryQuantityByProductId(product.getId()));
                dto.setAvailable(itemService.getAvailableInventoryQuantityByProductId(product.getId()));
                return dto;
            }).collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("products", dtoList);
            response.put("totalItem", products.getTotalElements());
            response.put("totalPage", products.getTotalPages());
            return response;
        } else {
            return new HashMap<>();
        }
    }

}
