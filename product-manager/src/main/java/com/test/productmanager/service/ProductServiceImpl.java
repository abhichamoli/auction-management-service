package com.test.productmanager.service;

import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.dto.EditProductRequest;
import com.test.productmanager.dto.ProductResponse;
import com.test.productmanager.entity.Category;
import com.test.productmanager.entity.Product;
import com.test.productmanager.exception.ProductManagerException;
import com.test.productmanager.exception.ResourceNotFoundException;
import com.test.productmanager.repository.CategoryRepository;
import com.test.productmanager.repository.ProductRepository;
import com.test.productmanager.common.Constants;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void addProduct(AddProductRequest addProductRequest) {
        String productCategory = addProductRequest.getCategory();
        Optional<Category> category = categoryRepository.findByCategoryName(productCategory);
        Category categoryValue = category.orElse(categoryService.addCategory(productCategory));
        Product product = createProduct(addProductRequest, categoryValue);
        productRepository.save(product);
    }

    @Override
    public ProductResponse findProduct(Long productId) throws ProductManagerException {
        Optional<Product> product = productRepository.findById(productId);
        Product productValue = product.orElseThrow(() -> {
            log.error("Product not found for ProductId: [{}]", productId);
            return new ResourceNotFoundException(String.format(Constants.ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_ID, productId));
        });
        return createProductResponse(productValue);
    }

    @Override
    public ProductResponse findProductByUUID(String productUUID) throws ProductManagerException {
        Optional<Product> product = productRepository.findByProductUUID(productUUID);
        Product productValue = product.orElseThrow(() -> {
            log.error("Product not found for ProductUUID: [{}]", productUUID);
            return new ResourceNotFoundException(String.format(Constants.ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID, productUUID));
        });
        return createProductResponse(productValue);
    }

    @Override
    public List<ProductResponse> findProductByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return products.stream().map(this::createProductResponse).toList();
    }

    @Override
    public List<ProductResponse> findAllproducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::createProductResponse).toList();
    }

    @Override
    public void deleteProduct(Long productId) throws ProductManagerException{
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            log.error("Product not found for ProductId: {}", product);
            throw new ResourceNotFoundException(String.format(Constants.ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_ID, productId));
        }
        productRepository.deleteById(productId);
    }

    @Override
    public void editProduct(Long productId, EditProductRequest editProductRequest) throws ProductManagerException {
        Optional<Product> product = productRepository.findById(productId);
        Product productValue = product.orElseThrow(() -> {
            log.error("Product not found for ProductId: [{}]", productId);
            return new ResourceNotFoundException(String.format(Constants.ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_ID, productId));
        });
        updateProductFields(productValue, editProductRequest);
        productRepository.save(productValue);
    }

    private ProductResponse createProductResponse(Product product) {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .basePrice(product.getBasePrice())
                .productUUID(product.getProductUUID())
                .addedOn(product.getAddedOn())
                .imageUrl(product.getImageUrl())
                .category(product.getCategory().getCategoryName())
                .build();
    }

    private Product createProduct(AddProductRequest addProductRequest, Category category) {
        return Product.builder()
                .productUUID(UUID.randomUUID().toString())
                .productName(addProductRequest.getProductName())
                .basePrice(addProductRequest.getBasePrice())
                .productDescription(addProductRequest.getProductDescription())
                .imageUrl(addProductRequest.getImageUrl())
                .addedOn(LocalDateTime.now())
                .category(category)
                .build();
    }

    private void updateProductFields(Product product, EditProductRequest editProductRequest) {
        if (StringUtils.isNotEmpty(editProductRequest.getProductName())) {
            product.setProductName(editProductRequest.getProductName());
        }
        if (StringUtils.isNotEmpty(editProductRequest.getProductDescription())) {
            product.setProductDescription(editProductRequest.getProductDescription());
        }
        if (Objects.nonNull(editProductRequest.getBasePrice())) {
            product.setBasePrice(editProductRequest.getBasePrice());
        }
    }

}
