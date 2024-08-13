package com.test.productmanager.controller;

import com.test.productmanager.common.TestHelper;
import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.dto.BaseResponse;
import com.test.productmanager.dto.ProductResponse;
import com.test.productmanager.exception.ProductManagerException;
import com.test.productmanager.exception.ResourceNotFoundException;
import com.test.productmanager.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static com.test.productmanager.common.Constants.ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID;
import static com.test.productmanager.common.Constants.MANDATORY_PARAMETERS_MISSING_FROM_ADD_PRODUCT_REQUEST;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCTS_FETCHED_SUCCESSFULLY;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCT_ADDED;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCT_FETCHED_SUCCESSFULLY;
import static com.test.productmanager.common.TestConstants.PRODUCT_UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Test
    public void testAddProduct() throws ProductManagerException {
        AddProductRequest addProductRequest = TestHelper.createAddProductRequest();
        Mockito.doNothing().when(productService).addProduct(addProductRequest);

        ResponseEntity<BaseResponse> responseEntity = productController.addProduct(addProductRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(SUCCESS_MSG_PRODUCT_ADDED, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

    @Test
    public void testAddProductForInvalidRequest() throws ProductManagerException {
        AddProductRequest addProductRequest = TestHelper.createAddProductRequest();
        addProductRequest.setProductName(null);

        ProductManagerException productManagerException = assertThrows(ProductManagerException.class, () -> productController.addProduct(addProductRequest));

        assertEquals(HttpStatus.BAD_REQUEST, productManagerException.getHttpStatus());
        assertEquals(MANDATORY_PARAMETERS_MISSING_FROM_ADD_PRODUCT_REQUEST, productManagerException.getMessage());

    }

    @Test
    public void testFindAllProducts() {
        List<ProductResponse> productResponses = List.of(TestHelper.createProductReponse());
        when(productService.findAllproducts()).thenReturn(productResponses);

        ResponseEntity<BaseResponse> responseEntity = productController.findAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(SUCCESS_MSG_PRODUCTS_FETCHED_SUCCESSFULLY, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

    @Test
    public void testFindProductByUUID() throws ProductManagerException {
        when(productService.findProductByUUID(PRODUCT_UUID)).thenReturn(TestHelper.createProductReponse());

        ResponseEntity<BaseResponse> responseEntity = productController.findProductByUUID(PRODUCT_UUID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(SUCCESS_MSG_PRODUCT_FETCHED_SUCCESSFULLY, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

    @Test
    public void testFindProductByUUIDNotFound() throws ProductManagerException {
        when(productService.findProductByUUID(PRODUCT_UUID)).thenThrow(new ResourceNotFoundException(String.format(ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID, PRODUCT_UUID)));

        ProductManagerException productManagerException = assertThrows(ProductManagerException.class, () -> productController.findProductByUUID(PRODUCT_UUID));

        assertEquals(HttpStatus.NOT_FOUND, productManagerException.getHttpStatus());
        assertEquals(String.format(ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID, PRODUCT_UUID), productManagerException.getMessage());
    }

}
