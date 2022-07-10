package com.noh.autocrypt.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private ExceptionEnum error;

    public ApiException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
