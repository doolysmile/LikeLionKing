package com.pbs.likelion.LikeLionKing.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class Post {

    private Long id; // 식별자
    private Long userId; // 작성자 ID, 외래키

    private int categoryId; // 게시글 종류
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    private LocalDateTime createdAt; // 생성날짜

    public Post(){}

    @Builder
    public Post(Long id, Long userId, int categoryId, String title, String content, Long views, Long recommended, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.views = views;
        this.recommended = recommended;
        this.createdAt = createdAt;
    }
}
