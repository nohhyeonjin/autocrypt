package com.noh.autocrypt.service;

import com.noh.autocrypt.domain.Member;
import com.noh.autocrypt.domain.Post;
import com.noh.autocrypt.repository.MemberRepository;
import com.noh.autocrypt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public boolean isMemberPost(Long postId, String email) {
        Member member = memberRepository.findByEmail(email);
        Post post = postRepository.findByMemberAndId(member, postId);

        if (post == null) {
            return false;
        } else {
            return true;
        }

    }

    @Transactional
    public Post modifyPost(Long postId, String content) {
        Optional<Post> oPost = postRepository.findById(postId);
        if (oPost.isEmpty()) {
            return null;
        }

        Post post = oPost.get();
        post.setContent(content);

        return post;
    }

}
