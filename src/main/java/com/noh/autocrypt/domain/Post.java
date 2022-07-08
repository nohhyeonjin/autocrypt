package com.noh.autocrypt.domain;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private String content;

    @NotNull
    private boolean locked = false;

}
