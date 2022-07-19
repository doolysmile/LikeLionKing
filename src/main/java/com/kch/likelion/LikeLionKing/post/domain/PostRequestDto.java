package com.kch.likelion.LikeLionKing.post.domain;


import lombok.Getter;

@Getter
public class PostRequestDto {

    private Long postSeq;
    private String title;
    private String content;

    public Post newPost(){
        if(postSeq != null){
            return new Post(this.postSeq, this.title, this.content);
        }
        return new Post(this.title, this.content);
    }
}
