package com.noh.autocrypt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private String content;

    @Column(nullable = false)
    private boolean locked = false;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Post(Member member, String content) {
        this.member = member;
        this.content = content;
    }

}
