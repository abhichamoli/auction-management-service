package com.test.auctionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class BidRequest {
    String userId;
    BigDecimal bidAmount;
    LocalDateTime bidTime;
}
