package com.test.productmanager.service;

import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.dto.EditProductRequest;
import com.test.productmanager.dto.ProductResponse;
import com.test.productmanager.exception.ProductManagerException;

import java.util.List;

public interface ProductService {

    void addProduct(AddProductRequest addProductRequest);

    ProductResponse findProduct(Long productId) throws ProductManagerException;

    ProductResponse findProductByUUID(String productUUID) throws ProductManagerException;

    List<ProductResponse> findAllproducts();

    List<ProductResponse> findProductByCategory(String categoryName) throws ProductManagerException;

    void deleteProduct(Long productId) throws ProductManagerException;

    void editProduct(Long productId, EditProductRequest editProductRequest) throws ProductManagerException;


}
