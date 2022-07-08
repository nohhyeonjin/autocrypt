package com.noh.autocrypt.service;

import com.noh.autocrypt.domain.Member;
import com.noh.autocrypt.domain.Post;
import com.noh.autocrypt.repository.MemberRepository;
import com.noh.autocrypt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Post addPost(String content, String email) {
        Member member = memberRepository.findByEmail(email);
        Post post = Post.builder()
                .member(member)
                .content(content)
                .build();

        postRepository.save(post);

        return post;
    }

}
