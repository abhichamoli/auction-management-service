package com.test.productmanager.common;

import com.test.productmanager.dto.AddProductRequest;
import com.test.productmanager.exception.BadRequestException;
import com.test.productmanager.exception.ProductManagerException;

import java.util.Objects;

import static com.test.productmanager.common.Constants.MANDATORY_PARAMETERS_MISSING_FROM_ADD_PRODUCT_REQUEST;

public class ProductManagerValidationUtil {
    private ProductManagerValidationUtil() {
        throw new IllegalStateException("Utility class");
    }
    public static void validateAddProductRequest(AddProductRequest addProductRequest) throws ProductManagerException {
        if (Objects.isNull(addProductRequest.getProductName()) || Objects.isNull(addProductRequest.getBasePrice())
                || Objects.isNull(addProductRequest.getCategory())) {
            throw new BadRequestException(MANDATORY_PARAMETERS_MISSING_FROM_ADD_PRODUCT_REQUEST);
        }
    }
}
