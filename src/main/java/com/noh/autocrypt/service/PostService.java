package com.noh.autocrypt.service;

import com.noh.autocrypt.domain.Member;
import com.noh.autocrypt.domain.Post;
import com.noh.autocrypt.repository.MemberRepository;
import com.noh.autocrypt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private static boolean UNLOCK = false;
    private static boolean LOCK = true;

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Post add(String content, String email) {
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
    public Post modify(Long postId, String content) {
        Optional<Post> oPost = postRepository.findById(postId);
        if (oPost.isEmpty()) {
            return null;
        }

        Post post = oPost.get();
        post.setContent(content);

        return post;
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public void changeLockStatus(Long id) {
        Optional<Post> oPost = postRepository.findById(id);
        Post post = oPost.get();

        if (post.isLocked()) {
            post.setLocked(UNLOCK);
        } else {
            post.setLocked(LOCK);
        }

    }

    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

}
