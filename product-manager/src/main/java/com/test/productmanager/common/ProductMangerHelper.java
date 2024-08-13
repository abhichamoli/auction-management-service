package com.test.productmanager.common;

import com.test.productmanager.dto.BaseResponse;
import org.springframework.http.HttpStatus;

public class ProductMangerHelper {

    private ProductMangerHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static void addSuccessMessageAndResponseCode(BaseResponse baseResponse, String successMessage) {
        baseResponse.setResponseCode(HttpStatus.OK.value());
        baseResponse.setResponseMessage(successMessage);
    }

}
