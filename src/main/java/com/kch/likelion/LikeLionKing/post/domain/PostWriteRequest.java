package com.kch.likelion.LikeLionKing.post.domain;


import lombok.Getter;

@Getter
public class PostWriteRequest {
    private Long seq;
    private String title;
    private String content;
}
