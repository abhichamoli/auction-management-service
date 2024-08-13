package com.test.bidservice.exception;

import org.springframework.http.HttpStatus;

public class ServiceUnAvailableException extends BidServiceException{

    public ServiceUnAvailableException(String message) {
        super(message, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
