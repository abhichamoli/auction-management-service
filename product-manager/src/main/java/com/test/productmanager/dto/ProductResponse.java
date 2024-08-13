package com.test.productmanager.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ProductResponse extends BaseResponse {
    private String productName;
    private String productDescription;
    private BigDecimal basePrice;
    private String productUUID;
    private LocalDateTime addedOn;
    private String imageUrl;
    private String category;
}
