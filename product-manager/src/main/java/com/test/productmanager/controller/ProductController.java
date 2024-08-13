package com.test.productmanager.controller;

import com.test.productmanager.common.ProductManagerValidationUtil;
import com.test.productmanager.common.ProductMangerHelper;
import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.dto.BaseResponse;
import com.test.productmanager.dto.EditProductRequest;
import com.test.productmanager.dto.ProductResponse;
import com.test.productmanager.dto.ProductsResponse;
import com.test.productmanager.exception.ProductManagerException;
import com.test.productmanager.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCTS_FETCHED_SUCCESSFULLY;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCT_ADDED;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCT_DELETED;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCT_EDITED;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_PRODUCT_FETCHED_SUCCESSFULLY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/products")
@Api(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, tags = "Product API")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @Operation(summary = "Add product in the stock")
    public ResponseEntity<BaseResponse> addProduct(@RequestBody AddProductRequest addProductRequest) throws ProductManagerException {
        ProductManagerValidationUtil.validateAddProductRequest(addProductRequest);
        productService.addProduct(addProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(HttpStatus.CREATED.value(), SUCCESS_MSG_PRODUCT_ADDED));
    }

    @GetMapping
    @Operation(summary = "Get list of all available products")
    public ResponseEntity<BaseResponse> findAllProducts() {
        List<ProductResponse> productResponses = productService.findAllproducts();
        ProductsResponse productsResponse = new ProductsResponse(productResponses);
        ProductMangerHelper.addSuccessMessageAndResponseCode(productsResponse, SUCCESS_MSG_PRODUCTS_FETCHED_SUCCESSFULLY);
        return ResponseEntity.ok().body(productsResponse);
    }

    @Hidden
    @GetMapping("/{productId}")
    public ResponseEntity<BaseResponse> findProduct(@PathVariable Long productId) throws Exception {
        ProductResponse productResponse = productService.findProduct(productId);
        ProductMangerHelper.addSuccessMessageAndResponseCode(productResponse, SUCCESS_MSG_PRODUCT_FETCHED_SUCCESSFULLY);
        return ResponseEntity.ok().body(productResponse);
    }

    @GetMapping("/productUUID/{productUUID}")
    @Operation(summary = "Get Product by UUID")
    public ResponseEntity<BaseResponse> findProductByUUID(@PathVariable String productUUID) throws ProductManagerException {
        ProductResponse productResponse = productService.findProductByUUID(productUUID);
        ProductMangerHelper.addSuccessMessageAndResponseCode(productResponse, SUCCESS_MSG_PRODUCT_FETCHED_SUCCESSFULLY);
        return ResponseEntity.ok().body(productResponse);
    }

    @GetMapping("/categoryName/{categoryName}")
    @Operation(summary = "Get Product by Category Name")
    public ResponseEntity<BaseResponse> findProductByCategory(@PathVariable String categoryName) throws ProductManagerException {
        List<ProductResponse> productResponses = productService.findProductByCategory(categoryName);
        ProductsResponse productsResponse = new ProductsResponse(productResponses);
        ProductMangerHelper.addSuccessMessageAndResponseCode(productsResponse, SUCCESS_MSG_PRODUCTS_FETCHED_SUCCESSFULLY);
        return ResponseEntity.ok().body(productsResponse);
    }

    @Hidden
    @DeleteMapping("/{productId}")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable Long productId) throws ProductManagerException {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().body(new BaseResponse(HttpStatus.OK.value(), String.format(SUCCESS_MSG_PRODUCT_DELETED, productId)));
    }

    @Hidden
    @PutMapping("/{productId}")
    public ResponseEntity<BaseResponse> editProduct(@PathVariable Long productId, @RequestBody EditProductRequest editProductRequest) throws Exception {
        productService.editProduct(productId, editProductRequest);
        return ResponseEntity.ok().body(new BaseResponse(HttpStatus.OK.value(), SUCCESS_MSG_PRODUCT_EDITED));
    }

}
