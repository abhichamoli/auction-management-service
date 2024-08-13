package com.test.auctionservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends AuctionServiceException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
