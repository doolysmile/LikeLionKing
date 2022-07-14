package com.kch.likelion.LikeLionKing.post.domain;

public class PostDto {

    private Long postSeq;

    private String content;

    private String title;

    public PostDto(Post post){
        this.postSeq = post.getPostSeq();
        this.content = post.getContent();
        this.title = post.getTitle();
    }
}
