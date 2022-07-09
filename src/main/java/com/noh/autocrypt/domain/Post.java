package com.noh.autocrypt.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private String content;

    @Column(nullable = false)
    private boolean locked = false;

    @Builder
    public Post(Member member, String content) {
        this.member = member;
        this.content = content;
    }

}
