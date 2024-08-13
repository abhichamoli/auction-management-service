package com.test.productmanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductManagerException extends Exception {

    final HttpStatus httpStatus;

    public ProductManagerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
