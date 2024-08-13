package com.test.auctionservice.common;

import java.math.BigDecimal;

public class TestConstants {
    public static final String PRODUCT_UUID = "123e4567-e89b-12d3-a456-426614174000";
    public static final Long SLOT_ID = 1L;
    public static String AUCTION_UUID = "123e4567-e89b-12d3-a456-426614174000";
    public static BigDecimal BASE_PRICE = new BigDecimal(10000);
    public static BigDecimal HIGHEST_BID = new BigDecimal(20000);
    public static String HIGHEST_BIDDER_ID = "abc@gmail.com";
    public static ProductAuctionStatusEnum PRODUCT_AUCTION_STATUS = ProductAuctionStatusEnum.INACTIVE;
    public static String PRODUCT_NAME = "Nike Shoes";
    public static final String PRODUCT_DESCRIPTION = "Air Jordans from 2010";
    public static final String USER_ID = "abc@gmail.com";

    private TestConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
