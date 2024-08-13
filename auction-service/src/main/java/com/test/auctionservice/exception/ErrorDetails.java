package com.test.auctionservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetails {
    private int responseCode;
    private String error;
}
