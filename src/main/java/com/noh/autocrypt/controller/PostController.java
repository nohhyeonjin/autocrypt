package com.noh.autocrypt.controller;

import com.noh.autocrypt.controller.dto.PostDTO;
import com.noh.autocrypt.controller.dto.PostDetailDTO;
import com.noh.autocrypt.domain.Post;
import com.noh.autocrypt.exception.ApiException;
import com.noh.autocrypt.exception.ExceptionEnum;
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
            throw new ApiException(ExceptionEnum.FORBIDDEN_MODIFY_POST);
        }

        Post post = postService.modify(id, postDTO.getContent());

        return post.getId();
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!postService.isMemberPost(id, userDetails.getUsername())){
            throw new ApiException(ExceptionEnum.FORBIDDEN_DELETE_POST);
        }

        postService.delete(id);
    }

    @GetMapping("/posts")
    public List<PostDetailDTO> getPosts(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<Post> postList = postService.getAllPosts();

        return postList.stream()
                .filter(post -> (!post.isLocked() || (post.isLocked() && postService.isMemberPost(post.getId(), userDetails.getUsername()))))
                .map(post -> new PostDetailDTO(post.getMember().getNickname(), post.getContent()))
                .collect(Collectors.toList());
    }

    @PostMapping("/post/{id}")
    public void lockPost(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!postService.isMemberPost(id, userDetails.getUsername())){
            throw new ApiException(ExceptionEnum.FORBIDDEN_LOCK_POST);
        }

        postService.changeLockStatus(id);
    }

}
