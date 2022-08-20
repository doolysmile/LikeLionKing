package com.hsy.likelion.LikeLionKing.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Post {
    private Long id;        // 게시글 id
    private Long memberId;  // 작성자 id
    private Integer categoryId; // 게시글 카테고리 id
    private String title;   // 게시글 제목
    private String content; // 게시글 내용
    private String createdAt; // 게시물 생성 일시
    private String updatedAt;    // 게시물 최종 수정 일시
    private Long views;     // 조회수
    private Long likes;     // 좋아요 수

    public Post() {
    }

    public Post(Long id, Long memberId, Integer categoryId, String title, String content, String createdAt, String updatedAt, Long views, Long likes) {
        this.id = id;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.views = views;
        this.likes = likes;
    }
}
