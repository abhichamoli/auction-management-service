package com.test.bidservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends BidServiceException {

    public ResourceNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

}
