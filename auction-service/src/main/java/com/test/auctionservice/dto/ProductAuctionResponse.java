package com.test.auctionservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.auctionservice.common.ProductAuctionStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAuctionResponse extends BaseResponse {
    private String productUUID;
    private String auctionUUID;
    private Long slotId;
    private BigDecimal basePrice;
    private BigDecimal highestbid;
    private String highestBidderId;
    private ProductAuctionStatusEnum status;
    private String productName;
}
