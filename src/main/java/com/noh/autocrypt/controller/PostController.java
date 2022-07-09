package com.noh.autocrypt.controller;

import com.noh.autocrypt.controller.dto.PostDTO;
import com.noh.autocrypt.controller.dto.PostDetailDTO;
import com.noh.autocrypt.domain.Post;
import com.noh.autocrypt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public Long addPost(@RequestBody PostDTO postDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Post post = postService.add(postDTO.getContent(), userDetails.getUsername());

        return post.getId();
    }

    @PatchMapping("/post/{id}")
    public Long modifyPost(@PathVariable Long id, @RequestBody PostDTO postDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!postService.isMemberPost(id, userDetails.getUsername())){
            throw new IllegalStateException("본인 게시글만 수정 가능합니다.");
        }

        Post post = postService.modify(id, postDTO.getContent());

        return post.getId();
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!postService.isMemberPost(id, userDetails.getUsername())){
            throw new IllegalStateException("본인 게시글만 삭제 가능합니다.");
        }

        postService.delete(id);
    }

    @GetMapping("/posts")
    public List<PostDetailDTO> getPosts(Authentication authentication) {
        List<Post> postList = postService.getAllPosts();

        return postList.stream()
                .map(post -> new PostDetailDTO(post.getMember().getNickname(), post.getContent()))
                .collect(Collectors.toList());
    }

    @PostMapping("/post/{id}")
    public void lockPost(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!postService.isMemberPost(id, userDetails.getUsername())){
            throw new IllegalStateException("본인 게시글만 상태 변경 가능합니다.");
        }

        postService.changeLockStatus(id);
    }

}
