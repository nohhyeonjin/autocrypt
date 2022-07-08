package com.noh.autocrypt.repository;

import com.noh.autocrypt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member save(Member member);

}

