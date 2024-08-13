package com.test.productmanager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditProductRequest {
    private String productName;
    private String productDescription;
    private BigDecimal basePrice;
}
