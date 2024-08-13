package com.test.auctionservice.common;

import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.entity.ProductAuction;
import com.test.auctionservice.kafka.KafkaWinMessage;
import org.springframework.http.HttpStatus;

public class AuctionServiceHelper {
    private AuctionServiceHelper() {
        throw new IllegalStateException("Utility class");
    }
    public static void addSuccessMessageAndResponseCode(BaseResponse baseResponse, String successMessage){
        baseResponse.setResponseCode(HttpStatus.OK.value());
        baseResponse.setResponseMessage(successMessage);
    }

    public static KafkaWinMessage createKafkaWinMessage(ProductAuction productAuction){
        return KafkaWinMessage.builder()
                .auctionUUID(productAuction.getAuctionUUID())
                .userId(productAuction.getHighestBidderId())
                .productUUID(productAuction.getProductUUID())
                .productName(productAuction.getProductName())
                .build();
    }
}
