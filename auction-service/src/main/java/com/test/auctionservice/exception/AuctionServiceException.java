package com.test.auctionservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuctionServiceException extends Exception {

    HttpStatus httpStatus;

    public AuctionServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
