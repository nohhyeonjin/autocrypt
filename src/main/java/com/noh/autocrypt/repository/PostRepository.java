package com.noh.autocrypt.repository;

import com.noh.autocrypt.domain.Member;
import com.noh.autocrypt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByMemberAndId(Member member, Long postId);

}
