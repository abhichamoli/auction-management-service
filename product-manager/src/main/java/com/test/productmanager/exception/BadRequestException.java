package com.test.productmanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends ProductManagerException{

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
