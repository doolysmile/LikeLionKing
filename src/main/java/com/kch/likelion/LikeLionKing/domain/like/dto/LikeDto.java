package com.kch.likelion.LikeLionKing.domain.like.dto;

import com.kch.likelion.LikeLionKing.domain.like.domain.Like;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LikeDto {
    private Long id;
    private Long userId;
    private Long postId;

    public static Like toEntity(LikeDto likeDto){
        return Like.builder()
                .userId(likeDto.getUserId())
                .postId(likeDto.getPostId())
                .build();
    }
}
