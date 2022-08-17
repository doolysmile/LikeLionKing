package com.pbs.likelion.LikeLionKing.domain.dto;

import com.pbs.likelion.LikeLionKing.domain.Likes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LikesDto {

    private Long id;
    private Long memberId;
    private Long postId;

    public static Likes toEntity(LikesDto likesDto) {

        Likes like = Likes.builder()
                .id(likesDto.getId())
                .memberId(likesDto.getMemberId())
                .postId(likesDto.getPostId())
                .build();
        return like;
    }

}
