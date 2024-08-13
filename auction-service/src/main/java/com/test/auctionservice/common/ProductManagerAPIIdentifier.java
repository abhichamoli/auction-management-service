package com.test.auctionservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductManagerAPIIdentifier {

    GET_PRODUCT_BY_UUID("/api/products/productUUID/{productUUID}");

    private String url;
}
