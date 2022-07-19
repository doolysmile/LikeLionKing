package com.hsy.likelion.LikeLionKing.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Post {
    private Long id;        // 게시글 id
    private String title;   // 게시글 제목
    private String content; // 게시글 내용

    public Post() {
    }

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
