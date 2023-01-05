package vn.sapo.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.brand.BrandMapper;
import vn.sapo.brand.BrandRepository;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.category.CategoryMapper;
import vn.sapo.category.CategoryRepository;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.product_pricing_policy.ProductPricingPolicyService;
import vn.sapo.shared.convert.Characters;
import vn.sapo.entities.product.*;
import vn.sapo.entities.tax.TaxType;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.item.ItemMapper;
import vn.sapo.item.ItemService;
import vn.sapo.media.MediaMapper;
import vn.sapo.media.MediaService;
import vn.sapo.product.dto.*;
import vn.sapo.product_tax.ProductTaxMapper;
import vn.sapo.product_tax.ProductTaxRepository;
import vn.sapo.product_tax.ProductTaxService;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.purchaseOrderItem.PurchaseOrderItemService;
import vn.sapo.tax.TaxMapper;
import vn.sapo.tax.TaxService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String BAR_CODE = "PVN";

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
    ProductTaxMapper productTaxMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTaxRepository productTaxRepository;

    @Autowired
    ProductPricingPolicyService productPricingPolicyService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    PurchaseOrderItemService purchaseOrderItemService;

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
                    dto.setTrading(itemService.getTradingQuantityByProductId(productId));
                    dto.setShipping(purchaseOrderItemService.getQuantityPurchaseByProductIdAndOrderStatusCode(product.getId(), "SHIPPING"));
                    dto.setInTransit(purchaseOrderItemService.getQuantityPurchaseByProductIdAndOrderStatusCode(product.getId(), "INTRANSIT"));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResult findById(Integer id) {
        Product product = productRepository.findByIdByDeletedIsFalse(id);
        if(product == null){
            throw new NotFoundException("Sản phẩm này đã bị xóa hoặc không tồn tại!");
        }
        Integer productId = product.getId();
        ProductResult dto = productMapper.toDTO(product);
        dto.setTotalInventory(itemService.getTotalInventoryQuantityByProductId(productId));
        dto.setAvailableInventory(itemService.getAvailableInventoryQuantityByProductId(productId));
        return dto;
    }

    @Override
    public ProductDetailResult findDetailById(Integer id) {
        Product product = productRepository.findById(id).get();
        ProductDetailResult productDetailResult = productMapper.toDTODetail(product);
        try {
            Optional<Category> optCategory = categoryRepository.findById(product.getCategoryId());
            if (optCategory.isPresent()) {
                productDetailResult.setCategory(categoryMapper.toDTO(optCategory.get()));
            }
        } catch (Exception e) {
            productDetailResult.setCategory(new CategoryResult());
        }

        try {
            Optional<Brand> optBrand = brandRepository.findById(product.getBrandId());
            if (optBrand.isPresent()) {
                productDetailResult.setBrand(brandMapper.toDTO(optBrand.get()));
            }
        } catch (Exception e) {
            productDetailResult.setBrand(new BrandResult());
        }

        Optional<Item> optItem = itemService.findByProductId(product.getId());
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
        if (createProductParam.getCategoryId() != null) {
            product.setCategoryId(createProductParam.getCategoryId());
        }
        if (createProductParam.getBrandId() != null) {
            product.setBrandId(createProductParam.getBrandId());
        }
        if (productRepository.findBySku(product.getSku().trim()).isPresent()) {
            throw new NotFoundException("Mã SKU đã tồn tại");
        }
        if (productRepository.findByBarCode(product.getBarCode().trim()).isPresent()) {
            throw new NotFoundException("Mã BarCode đã tồn tại");
        }
        product = productRepository.save(product);
        if (product.getSku().trim().equals("")) {
            Random generator = new Random();
            int value = generator.nextInt(9999) + 1;
            product.setSku(BAR_CODE + value);
        }
        if (product.getBarCode().trim().equals("")) {
            product.setBarCode(product.getSku());
        }
        Integer productId = product.getId();
        productTaxService.createAll(createProductParam.getTaxList(), product);
        productPricingPolicyService.createAll(createProductParam.getPriceList(), productId);
        if (createProductParam.getMediaList().size() != 0) {
            mediaService.save(createProductParam.getMediaList(), product);
        }
        if (createProductParam.isEnableVariant()) {
            System.out.println(createProductParam);
            itemService.create(itemMapper.toDTO(createProductParam, productId, 1));
        }
        return productMapper.toDTO(product);
    }

    @Override
    @Transactional
    public void update(UpdateProductParam updateProductParam) {

        Integer productId = updateProductParam.getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        product.setStatus(updateProductParam.isEnableSell() ? ProductStatus.AVAILABLE :
                ProductStatus.UNAVAILABLE);
        if(updateProductParam.getSku().equals("")){
            updateProductParam.setSku(product.getSku());
        }

        if(updateProductParam.getBarCode().equals("")){
            updateProductParam.setBarCode(product.getBarCode());
        }

        if(!product.getSku().equals(updateProductParam.getSku())){
            if (productRepository.findBySku(updateProductParam.getSku()).isPresent()) {
                throw new NotFoundException("Mã SKU đã tồn tại");
            }
        }

        if(!product.getBarCode().equals(updateProductParam.getBarCode())){
            if (productRepository.findByBarCode(updateProductParam.getBarCode()).isPresent()) {
                throw new NotFoundException("Mã BarCode đã tồn tại");
            }
        }

        productMapper.transferFields(updateProductParam, product);
        if (updateProductParam.isApplyTax()) {
            productTaxService.deleteAllByProductId(productId);
            productTaxService.createAll(updateProductParam.getTaxList(), product);
        } else {
            productTaxService.deleteAllByProductId(productId);
            List<ProductTaxParam> productTaxParams = new ArrayList<>();
            productTaxParams.add(0, new ProductTaxParam(1, TaxType.TAX_PURCHASE));
            productTaxParams.add(1, new ProductTaxParam(1, TaxType.TAX_SALE));
            productTaxService.createAll(productTaxParams, product);
        }
        if (updateProductParam.getMediaList().size() == 0) {
            mediaService.deleteAllByProductId(productId);
        }
        if (updateProductParam.getMediaList().size() != 0) {
            mediaService.deleteAllByProductId(productId);
            mediaService.save(updateProductParam.getMediaList(), product);
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
    @Transactional
    public Map<String, Object> getAllProductItemPage(Integer pageNo, Integer pageSize, String title,
                                                     Integer categoryId, Integer brandId, String status,
                                                     String typeSort, String nameFieldSort) {
        pageNo = pageNo - 1;

        Pageable pageable;
        if (typeSort.equals("asc")) {
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
        } else if (categoryId == -1 && status.equals("")) {
            products = productRepository.findAllByTitleContainingAndBrandId(brandId, title, pageable);
        } else if(categoryId == -1 && brandId == -1) {
            products = productRepository.findAllByTitleContainingAndStatus(ProductStatus.parseProductStatus(status), title, pageable);
        } else if(status.equals("")){
            products = productRepository.findAllByTitleContainingAndCategoryIdAndBrandId(title, categoryId, brandId, pageable);
        } else if (brandId == -1) {
            products = productRepository.findAllByTitleContainingAndCategoryIdAndStatus(title, categoryId, ProductStatus.parseProductStatus(status), pageable);
        } else if (categoryId == -1) {
            products = productRepository.findAllByTitleContainingAndBrandIdAndStatus(title, brandId, ProductStatus.parseProductStatus(status), pageable );
        } else {
            products = productRepository.findAllByTitleContainingAndCategoryIdAndBrandIdAndStatus(title, categoryId, brandId,ProductStatus.parseProductStatus(status), pageable);
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
    @Transactional
    public Map<String, Object> getAllProductVariantPage(Integer pageNo, Integer pageSize, String title,
                                                        Integer categoryId, Integer brandId, String status,
                                                        String typeSort, String nameFieldSort) {
        pageNo = pageNo - 1;

        Pageable pageable;
        if (typeSort.equals("asc")) {
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
        } else if (categoryId == -1 && status.equals("")) {
            products = productRepository.findAllByTitleContainingAndBrandId(brandId, title, pageable);
        } else if(categoryId == -1 && brandId == -1) {
            products = productRepository.findAllByTitleContainingAndStatus(ProductStatus.parseProductStatus(status), title, pageable);
        } else if(status.equals("")){
            products = productRepository.findAllByTitleContainingAndCategoryIdAndBrandId(title, categoryId, brandId, pageable);
        } else if (brandId == -1) {
            products = productRepository.findAllByTitleContainingAndCategoryIdAndStatus(title, categoryId, ProductStatus.parseProductStatus(status), pageable);
        } else if (categoryId == -1) {
            products = productRepository.findAllByTitleContainingAndBrandIdAndStatus(title, brandId, ProductStatus.parseProductStatus(status), pageable );
        } else {
            products = productRepository.findAllByTitleContainingAndCategoryIdAndBrandIdAndStatus(title, categoryId, brandId,ProductStatus.parseProductStatus(status), pageable);
        }
        if (products.hasContent()) {
            List<Product> productList = products.getContent();
            List<ProductVariantsResult> productVariantsResults = new ArrayList<>();
            for (Product product : productList) {
                ProductVariantsResult productVariantsResult = productMapper.toDTOVariants(product);
                productVariantsResult.setImage(mediaService.getLinkMediaByProductIdIsMain(product.getId()));
                productVariantsResult.setInventory(itemService.getTotalInventoryQuantityByProductId(product.getId()));
                productVariantsResult.setAvailable(itemService.getAvailableInventoryQuantityByProductId(product.getId()));
                productVariantsResult.setTrading(itemService.getTradingQuantityByProductId(product.getId()));
                productVariantsResult.setInTransit(purchaseOrderItemService.getQuantityPurchaseByProductIdAndOrderStatusCode(product.getId(), "INTRANSIT"));
                productVariantsResult.setShipping(purchaseOrderItemService.getQuantityPurchaseByProductIdAndOrderStatusCode(product.getId(), "SHIPPING"));
                productVariantsResults.add(productVariantsResult);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("products", productVariantsResults);
            response.put("totalItem", products.getTotalElements());
            response.put("totalPage", products.getTotalPages());
            return response;
        } else {
            return new HashMap<>();
        }
    }

    @Override
    @Transactional
    public void changeStatusToAvailable(List<Integer> productIds, boolean status) {
        for (Integer productId : productIds) {
            Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
            product.setStatus(status ? ProductStatus.AVAILABLE : ProductStatus.UNAVAILABLE);
        }
    }

    @Override
    @Transactional
    public void deleteSoftProduct(List<Integer> productIds) {
        for (Integer productId : productIds) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product newProduct = product.get();
                newProduct.setDeleted(true);
            } else {
                throw new NotFoundException("Product not found");
            }
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product newProduct = product.get();
            newProduct.setDeleted(true);
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    @Transactional
    public void saveChangeApplyTax(Integer applyTax, List<Integer> productIds) {
        for (Integer productId : productIds) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product newProduct = product.get();
                if (applyTax == 1 && product.get().isApplyTax() == false) {
                    newProduct.setApplyTax(true);
                } else if (applyTax == 0 && product.get().isApplyTax() == true) {
                    newProduct.setApplyTax(false);
                }
            } else {
                throw new NotFoundException("Product not found");
            }
        }
    }

    @Override
    @Transactional
    public List<ProductVariantsResult> getAllCheckInventoryProduct(List<Integer> productIds) {
        List<ProductVariantsResult> productVariantsResults = new ArrayList<>();
        for (Integer item : productIds) {
            Optional<Product> productOptional = productRepository.findById(item);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                ProductVariantsResult productVariantsResult = productMapper.toDTOVariants(product);
                productVariantsResult.setInventory(itemService.getTotalInventoryQuantityByProductId(product.getId()));
                productVariantsResult.setAvailable(itemService.getAvailableInventoryQuantityByProductId(product.getId()));
                productVariantsResult.setTrading(itemService.getTradingQuantityByProductId(product.getId()));
                productVariantsResult.setInTransit(purchaseOrderItemService.getQuantityPurchaseByProductIdAndOrderStatusCode(product.getId(), "INTRANSIT"));
                productVariantsResult.setShipping(purchaseOrderItemService.getQuantityPurchaseByProductIdAndOrderStatusCode(product.getId(), "SHIPPING"));
                productVariantsResults.add(productVariantsResult);
            } else {
                throw new NotFoundException("Product not found");
            }
        }
        return productVariantsResults;
    }

}
