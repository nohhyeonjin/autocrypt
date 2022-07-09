package com.noh.autocrypt.controller.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
public class JoinDTO {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;

}
