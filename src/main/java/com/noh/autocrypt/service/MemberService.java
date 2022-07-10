package com.noh.autocrypt.service;

import com.noh.autocrypt.domain.Member;
import com.noh.autocrypt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String email, String password, String nickname) {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return member;
    }

    public boolean existMember(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname);
        if (member == null) {
            return false;
        } else {
            return true;
        }
    }

}
