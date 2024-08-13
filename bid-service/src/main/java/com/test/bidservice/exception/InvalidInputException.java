package com.test.bidservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidInputException extends BidServiceException {

    public InvalidInputException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
