package com.test.productmanager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.test.productmanager.common.Constants.ERROR_MSG_INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ProductManagerExceptionHandler {
    @ExceptionHandler(ProductManagerException.class)
    public ResponseEntity<ErrorDetails> handleProductManagerException(ProductManagerException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getHttpStatus().value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
        log.error("Exception occured: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_MSG_INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
