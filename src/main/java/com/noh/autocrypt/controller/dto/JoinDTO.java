package com.noh.autocrypt.controller.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class JoinDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

}
