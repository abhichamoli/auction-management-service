package com.test.bidservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BidServiceException extends Exception {

    HttpStatus httpStatus;

    public BidServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
