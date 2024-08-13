package com.test.auctionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends BaseResponse {
    private String productName;
    private String productDescription;
    private BigDecimal basePrice;
}
