package vn.sapo.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.brand.BrandMapper;
import vn.sapo.brand.BrandRepository;
import vn.sapo.category.CategoryMapper;
import vn.sapo.category.CategoryRepository;
import vn.sapo.convert.Characters;
import vn.sapo.entities.product.*;
import vn.sapo.exceptions.NotFoundException;
import vn.sapo.item.ItemMapper;
import vn.sapo.item.ItemService;
import vn.sapo.media.MediaMapper;
import vn.sapo.media.MediaService;
import vn.sapo.media.dto.MediaResult;
import vn.sapo.product.dto.*;
import vn.sapo.product_tax.ProductTaxRepository;
import vn.sapo.product_tax.ProductTaxService;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.TaxMapper;
import vn.sapo.tax.TaxService;

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

    @Autowired
    Characters characters;


    @Override
    @Transactional(readOnly = true)
    public List<ProductResult> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    Integer productId = product.getId();
                    ProductResult dto = productMapper.toDTO(product);
                    dto.setTotalInventory(itemService.getTotalInventoryQuantityByProductId(productId));
                    dto.setAvailableInventory(itemService.getAvailableInventoryQuantityByProductId(productId));
                    return dto;
                })
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public ProductResult findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDetailResult findDetailById(Integer id) {
        Product product = productRepository.findById(id).get();
        ProductDetailResult productDetailResult = productMapper.toDTODetail(product);
        Optional<Category> optCategory = categoryRepository.findById(product.getCategoryId());
        Optional<Brand> optBrand = brandRepository.findById(product.getBrandId());
        Optional<Item> optItem = itemService.findByProductId(product.getId());
        if (optCategory.isPresent()) {
            productDetailResult.setCategory(categoryMapper.toDTO(optCategory.get()));
        }
        if (optBrand.isPresent()) {
            productDetailResult.setBrand(brandMapper.toDTO(optBrand.get()));
        }

        productDetailResult.setMediaResults(mediaService.findAllById(product.getId()));
        if (optItem.isPresent()) {
            productDetailResult.setItemResult(itemMapper.toDTO(optItem.get()));
        }
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
        Product product = productMapper.toModel(createProductParam);
        product.setStatus(
                createProductParam.isEnableSell() ? ProductStatus.AVAILABLE :
                        ProductStatus.UNAVAILABLE);
        if(createProductParam.getCategoryId() != null) {
            product.setCategoryId(createProductParam.getCategoryId());
        }
        if(createProductParam.getBrandId() != null) {
            product.setBrandId(createProductParam.getBrandId());
        }
        if (product.getSku().trim().equals("")) {
            product.setSku(characters.covertToString(createProductParam.getTitle()));
        }
        if (product.getBarCode().trim().equals("")) {
            Random random = new Random();
            product.setBarCode(String.valueOf(random.nextInt(99999999) + 1));
        }
        product = productRepository.save(product);
        Integer productId = product.getId();
        if (createProductParam.isApplyTax()) {
            productTaxService.create(createProductParam.getTaxList(), productId);
        }
        System.out.println(createProductParam.getMediaList().size());
        if (createProductParam.getMediaList().size() != 0) {
            mediaService.save(createProductParam.getMediaList(), product);
        }
        if (createProductParam.isEnableVariant()) {
            itemService.create(itemMapper.toDTO(createProductParam, productId, 1));
        }
        return productMapper.toDTO(product);
    }

    @Override
    @Transactional
    public ProductResult update(ProductUpdateParam productUpdateParam) {
        Integer productId = productUpdateParam.getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productMapper.transferFields(productUpdateParam, product);

        return productMapper.toDTO(product);
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
    @Transactional
    public Map<String, Object> getAllProductItemPage(Integer pageNo, Integer pageSize, String title,
                                                     Integer categoryId, Integer brandId, String status,
                                                     String typeSort, String nameFieldSort ) {
        pageNo = pageNo - 1;

        Pageable pageable;
        if(typeSort.equals("asc")){
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(nameFieldSort).ascending());
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(nameFieldSort).descending());
        }

        Page<Product> products;
        if (title.equals("") && categoryId == -1 && brandId == -1 && status.equals("")) {
            products = productRepository.findAllByDeletedIsFalse(pageable);
        } else if (categoryId == -1 && brandId == -1 && status.equals("")) {
            products = productRepository.findAllByTitleContaining(title, pageable);
        } else if (brandId == -1 && status.equals("")) {
            products = productRepository.findAllByTitleContainingAndCategoryId(categoryId, title, pageable);
        } else if (status.equals("")) {
            products = productRepository.findAllByTitleContainingAndBrandId(brandId, title, pageable);
        } else {
            products = productRepository.findAllByTitleContainingAndStatus(ProductStatus.parseProductStatus(status), title, pageable);
        }
        if (products.hasContent()) {
            List<Product> productList = products.getContent();
            List<ProductItemResult> productItemResults = new ArrayList<>();
            for (Product product : productList) {
                ProductItemResult productItemResult = productMapper.toDTOPage(product);
                productItemResult.setImage(mediaService.getLinkMediaByProductIdIsMain(product.getId()));
                productItemResult.setInventory(itemService.getTotalInventoryQuantityByProductId(product.getId()));
                productItemResult.setAvailable(itemService.getAvailableInventoryQuantityByProductId(product.getId()));
                productItemResults.add(productItemResult);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("products", productItemResults);
            response.put("totalItem", products.getTotalElements());
            response.put("totalPage", products.getTotalPages());
            return response;
        } else {
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, Object> getAllProductVariantPage(Integer pageNo, Integer pageSize, String title,
                                                        Integer categoryId, Integer brandId, String status,
                                                        String typeSort, String nameFieldSort) {
        return null;
    }

    @Override
    @Transactional
    public void saveChangeStatusToAvailable(List<String> list) {
        for (String item : list) {
            Optional<Product> product = productRepository.findById(Integer.valueOf(item));
            if (product.isPresent()) {
                Product newProduct = product.get();
                newProduct.setStatus(ProductStatus.parseProductStatus("AVAILABLE"));
                productRepository.save(newProduct);
            }
        }
    }

    @Override
    @Transactional
    public void saveChangeStatusToUnavailable(List<String> list) {
        for (String item : list) {
            Optional<Product> product = productRepository.findById(Integer.valueOf(item));
            if (product.isPresent()) {
                Product newProduct = product.get();
                newProduct.setStatus(ProductStatus.parseProductStatus("UNAVAILABLE"));
                productRepository.save(newProduct);
            }
        }
    }

    @Override
    @Transactional
    public void deleteSoftProduct(List<String> list) {
        for (String item : list) {
            Optional<Product> product = productRepository.findById(Integer.valueOf(item));
            if (product.isPresent()) {
                Product newProduct = product.get();
                newProduct.setDeleted(true);
                productRepository.save(newProduct);
            }
        }
    }

}
