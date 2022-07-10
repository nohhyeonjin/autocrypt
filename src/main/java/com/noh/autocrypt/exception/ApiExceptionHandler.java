package com.noh.autocrypt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException e) {
        ApiExceptionEntity exceptionEntity = ApiExceptionEntity.builder()
                .errorCode(e.getError().getCode())
                .message(e.getError().getMessage())
                .build();

        return ResponseEntity
                .status(e.getError().getStatus())
                .body(exceptionEntity);
    }

}