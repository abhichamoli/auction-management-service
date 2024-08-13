package com.test.bidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
@ToString
public class BidRequest {
    String userId;
    BigDecimal bidAmount;
    LocalDateTime bidTime;
}
