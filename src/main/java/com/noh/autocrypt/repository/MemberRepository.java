package com.noh.autocrypt.repository;

import com.noh.autocrypt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByNickname(String nickname);

}