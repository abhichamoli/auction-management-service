package com.test.productmanager.common;

import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.dto.ProductResponse;
import com.test.productmanager.entity.Category;
import com.test.productmanager.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.test.productmanager.common.TestConstants.BASE_PRICE;
import static com.test.productmanager.common.TestConstants.CATEGORY;
import static com.test.productmanager.common.TestConstants.IMAGE_URL;
import static com.test.productmanager.common.TestConstants.PRODUCT_DESCRIPTION;
import static com.test.productmanager.common.TestConstants.PRODUCT_NAME;
import static com.test.productmanager.common.TestConstants.PRODUCT_UUID;

public class TestHelper {
    private TestHelper() {
        throw new IllegalStateException("Utility Class");
    }

    public static AddProductRequest createAddProductRequest(){
        return AddProductRequest.builder()
                .productName(PRODUCT_NAME)
                .productDescription(PRODUCT_DESCRIPTION)
                .category(CATEGORY)
                .basePrice(new BigDecimal(10000))
                .imageUrl(IMAGE_URL)
                .build();
    }

    public static ProductResponse createProductReponse() {
        return ProductResponse.builder()
                .productUUID(PRODUCT_UUID)
                .productName(PRODUCT_NAME)
                .addedOn(LocalDateTime.now())
                .productDescription(PRODUCT_DESCRIPTION)
                .basePrice(BASE_PRICE)
                .category(CATEGORY)
                .imageUrl(IMAGE_URL)
                .build();
    }

    public static Product createProduct(){
        return Product.builder()
                .productName(PRODUCT_NAME)
                .productUUID(PRODUCT_UUID)
                .addedOn(LocalDateTime.now())
                .productDescription(PRODUCT_DESCRIPTION)
                .basePrice(BASE_PRICE)
                .category(new Category(CATEGORY))
                .imageUrl(IMAGE_URL)
                .build();
    }
}
