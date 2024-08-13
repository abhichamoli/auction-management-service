package com.test.auctionservice.kafka;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KafkaWinMessage {
    String userId;
    String auctionUUID;
    String productUUID;
    String productName;
}
