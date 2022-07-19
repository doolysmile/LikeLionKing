package com.kch.likelion.LikeLionKing.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostDto {

    private Long postSeq;

    private Long userSeq;

    private int views;

    private int likes;
    private String title;

    private String content;

    private BoardType boardType;

    private LocalDateTime createAt;

    public PostDto(Post post){
        this.postSeq = post.getPostSeq();
        this.userSeq = post.getUserSeq();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.boardType = post.getBoardType();
        this.createAt = post.getCreateAt();
    }
}
