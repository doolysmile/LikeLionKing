package com.kch.likelion.LikeLionKing.post.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class PostDto {

    private Long postSeq;

    private Long userSeq;

    private int views;

    private int likes;
    private String title;

    private String content;

    private BoardType boardType;

    private LocalDateTime createAt;

    public static PostDto toDto(Post post){
        PostDto postDto = PostDto.builder()
                .postSeq(post.getPostSeq())
                .userSeq(post.getUserSeq())
                .views(post.getViews())
                .likes(post.getLikes())
                .title(post.getTitle())
                .content(post.getContent())
                .boardType(post.getBoardType())
                .createAt(post.getCreateAt())
                .build();
        return postDto;
    }

}
