package com.hsy.likelion.LikeLionKing.dto.likes;

import com.hsy.likelion.LikeLionKing.domain.Likes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LikesDto {
    private Long id;
    private Long memberId; // 회원 id
    private Long postId;   //  게시물 id

    public static Likes toEntity(LikesDto likesDto) {
        return Likes.builder()
                .memberId(likesDto.getMemberId())
                .postId(likesDto.getPostId())
                .build();
    }
}
