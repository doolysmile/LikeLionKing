package com.study.LikeLionKing.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCreateRequest {
    Long postId;
    Long userId;
}
