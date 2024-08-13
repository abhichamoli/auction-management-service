package com.test.auctionservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends AuctionServiceException {

    public ResourceNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

}
