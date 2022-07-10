package com.noh.autocrypt.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiExceptionEntity {

    private String errorCode;

    private String message;

    @Builder
    public ApiExceptionEntity(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
