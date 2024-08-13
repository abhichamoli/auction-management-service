package com.test.auctionservice.common;

import com.test.auctionservice.dto.AddSlotRequest;
import com.test.auctionservice.dto.AuctionSlotResponse;
import com.test.auctionservice.dto.BidRequest;
import com.test.auctionservice.dto.ProductAuctionResponse;
import com.test.auctionservice.dto.ProductResponse;
import com.test.auctionservice.entity.AuctionSlot;
import com.test.auctionservice.entity.ProductAuction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.test.auctionservice.common.TestConstants.*;

public class TestHelper {
    private TestHelper() {
        throw new IllegalStateException("Utility Class");
    }

    public static ProductAuctionResponse createProductAuctionResponse() {
        return ProductAuctionResponse.builder()
                .productUUID(PRODUCT_UUID)
                .highestBidderId(HIGHEST_BIDDER_ID)
                .slotId(SLOT_ID)
                .basePrice(BASE_PRICE)
                .highestbid(HIGHEST_BID)
                .auctionUUID(AUCTION_UUID)
                .productName(PRODUCT_NAME)
                .status(PRODUCT_AUCTION_STATUS)
                .build();
    }

    public static AddSlotRequest createAddSlotRequest() {
        return new AddSlotRequest(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public static AuctionSlotResponse createAuctionSlotResponse() {
        return new AuctionSlotResponse(LocalDateTime.now(), LocalDateTime.now().plusDays(1), SlotStatusEnum.CLOSE, SLOT_ID);
    }

    public static ProductResponse createProductResposne(){
        return new ProductResponse(PRODUCT_NAME, PRODUCT_DESCRIPTION, BASE_PRICE);
    }

    public static AuctionSlot createAuctionSlot(){
        return AuctionSlot.builder()
                .slotId(SLOT_ID)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1))
                .status(SlotStatusEnum.CLOSE)
                .build();

    }

    public static BidRequest createBidRequest(){
        return new BidRequest(USER_ID, new BigDecimal(30000), LocalDateTime.now());
    }

    public static ProductAuction createProductAuction(){
        return  ProductAuction.builder()
                .productUUID(PRODUCT_UUID)
                .highestBidderId(HIGHEST_BIDDER_ID)
                .basePrice(BASE_PRICE)
                .highestbid(HIGHEST_BID)
                .auctionUUID(AUCTION_UUID)
                .productName(PRODUCT_NAME)
                .status(ProductAuctionStatusEnum.ACTIVE)
                .build();
    }
}
