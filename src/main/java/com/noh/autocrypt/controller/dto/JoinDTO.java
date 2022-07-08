package com.noh.autocrypt.controller.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class JoinDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String nickname;

}
