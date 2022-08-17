package com.pbs.likelion.LikeLionKing.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Likes {

    private Long id;
    private Long memberId;
    private Long postId;

}
