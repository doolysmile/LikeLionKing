package com.kch.likelion.LikeLionKing.domain.like.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Like {
    private Long likeSeq;
    private Long userId;
    private Long postId;

    @Builder
    public Like(Long likeSeq, Long userId, Long postId){
        this.likeSeq = likeSeq;
        this.userId = userId;
        this.postId = postId;
    }
}
