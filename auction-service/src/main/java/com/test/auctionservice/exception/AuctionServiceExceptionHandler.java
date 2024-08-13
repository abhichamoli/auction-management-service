package com.test.auctionservice.exception;

import com.test.auctionservice.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AuctionServiceExceptionHandler {
    @ExceptionHandler(AuctionServiceException.class)
    public ResponseEntity<ErrorDetails> handleAuctionServiceException(AuctionServiceException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getHttpStatus().value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
        log.error("Exception occured: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.ERROR_MSG_INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
