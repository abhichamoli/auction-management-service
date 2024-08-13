package com.test.notificationservice.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class KafkaWinMessage {
    String userId;
    String auctionUUID;
    String productUUID;
    String productName;
}
