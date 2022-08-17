package com.study.LikeLionKing.domain.dto;

import com.study.LikeLionKing.domain.Like;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeDto {
    long id;
    long userId;
    long postId;

    public Like toEntity(){
        Like like = Like.builder()
                .id(id)
                .postId(postId)
                .userId(userId)
                .build();
        return like;
    }

    @Builder
    public LikeDto(long id, long userId, long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }
}
