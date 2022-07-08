package com.noh.autocrypt.repository;

import com.noh.autocrypt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post save(Post post);

}
