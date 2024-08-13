package com.test.bidservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BidResponse extends BaseResponse{
    private String auctionUUID;
    private String userId;
    private BigDecimal bidAmount;
    private LocalDateTime bidTime;
}
