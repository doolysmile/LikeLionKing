package com.hsy.likelion.LikeLionKing.likes.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Likes {
    private Long id;
    private Long memberId; // 회원 id
    private Long postId;   //  게시물 id
}
