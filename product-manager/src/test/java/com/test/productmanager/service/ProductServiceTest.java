package com.test.productmanager.service;

import com.test.productmanager.common.TestHelper;
import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.dto.ProductResponse;
import com.test.productmanager.entity.Category;
import com.test.productmanager.entity.Product;
import com.test.productmanager.exception.ProductManagerException;
import com.test.productmanager.repository.CategoryRepository;
import com.test.productmanager.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.test.productmanager.common.Constants.ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID;
import static com.test.productmanager.common.TestConstants.BASE_PRICE;
import static com.test.productmanager.common.TestConstants.CATEGORY;
import static com.test.productmanager.common.TestConstants.PRODUCT_NAME;
import static com.test.productmanager.common.TestConstants.PRODUCT_UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryService categoryService;

    @Test
    public void testAddProduct() {
        AddProductRequest addProductRequest = TestHelper.createAddProductRequest();
        Category category = new Category(CATEGORY);
        Optional<Category> categoryOptional = Optional.of(category);

        Mockito.when(categoryRepository.findByCategoryName(CATEGORY)).thenReturn(categoryOptional);
        Mockito.when(categoryService.addCategory(CATEGORY)).thenReturn(category);

        productService.addProduct(addProductRequest);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    public void testFindProductByUUID() throws ProductManagerException {
        Optional<Product> product = Optional.of(TestHelper.createProduct());
        Mockito.when(productRepository.findByProductUUID(PRODUCT_UUID)).thenReturn(product);

        ProductResponse productResponse = productService.findProductByUUID(PRODUCT_UUID);
        assertEquals(PRODUCT_NAME, productResponse.getProductName());
        assertEquals(BASE_PRICE, productResponse.getBasePrice());
    }

    @Test
    public void testFindProductByUUIDNotFound() throws ProductManagerException {
        Optional<Product> product = Optional.ofNullable(null);
        Mockito.when(productRepository.findByProductUUID(PRODUCT_UUID)).thenReturn(product);

        ProductManagerException productManagerException = assertThrows(ProductManagerException.class, () -> productService.findProductByUUID(PRODUCT_UUID));

        assertEquals(HttpStatus.NOT_FOUND, productManagerException.getHttpStatus());
        assertEquals(String.format(ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID, PRODUCT_UUID), productManagerException.getMessage());

    }
}
