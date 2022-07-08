package com.noh.autocrypt.controller;

import com.noh.autocrypt.auth.PrincipalDetails;
import com.noh.autocrypt.controller.dto.AddPostDTO;
import com.noh.autocrypt.domain.Post;
import com.noh.autocrypt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public Long addPost(@RequestBody AddPostDTO addPostDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Post post = postService.addPost(addPostDTO.getContent(), userDetails.getUsername());

        return post.getId();
    }

}
