package com.noh.autocrypt.controller;

import com.noh.autocrypt.controller.dto.JoinDTO;
import com.noh.autocrypt.domain.Member;
import com.noh.autocrypt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/member")
    public Long join(@RequestBody @Valid JoinDTO joinDto) {
        String rawPassword = joinDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        Member member = memberService.join(joinDto.getEmail(), encPassword, joinDto.getNickname());

        return member.getId();
    }

}
