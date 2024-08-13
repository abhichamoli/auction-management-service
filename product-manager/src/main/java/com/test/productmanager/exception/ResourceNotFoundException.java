package com.test.productmanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends ProductManagerException{

    public ResourceNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

}
