package com.hsy.likelion.LikeLionKing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    @JsonProperty("id")
    private Long id;        // 게시글 id
    @JsonProperty("title")
    private String title;   // 게시글 제목
    @JsonProperty("content")
    private String content; // 게시글 내용

    public Post() {
    }

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
