package com.noh.autocrypt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    EXIST_MEMBER(HttpStatus.BAD_REQUEST, "m001", "이미 존재하는 회원입니다"),
    EXIST_NICKNAME(HttpStatus.BAD_REQUEST, "m002", "이미 존재하는 닉네임입니다"),

    FORBIDDEN_MODIFY_POST(HttpStatus.FORBIDDEN, "p001", "본인 게시글만 수정 가능합니다"),
    FORBIDDEN_DELETE_POST(HttpStatus.FORBIDDEN, "p002", "본인 게시글만 삭제 가능합니다"),
    FORBIDDEN_LOCK_POST(HttpStatus.FORBIDDEN, "p003", "본인 게시글만 잠금 가능합니다"),
    FORBIDDEN_READ_POST(HttpStatus.FORBIDDEN, "p004", "잠금 게시글입니다");

    private HttpStatus status;
    private String code;
    private String message;

}
