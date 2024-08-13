package com.test.productmanager.common;

import java.math.BigDecimal;

public class TestConstants {
    public static final String PRODUCT_UUID = "123e4567-e89b-12d3-a456-426614174000";
    public static final String PRODUCT_NAME  = "Nike shoes";
    public static final BigDecimal BASE_PRICE = new BigDecimal(10000);
    public static final String IMAGE_URL  = "https://image.com";
    public static final String CATEGORY  = "FOOTWEAR";
    public static final String PRODUCT_DESCRIPTION = "Air Jordans from 2010";

    private TestConstants() {
        throw new IllegalStateException("Utility Class");
    }

}
