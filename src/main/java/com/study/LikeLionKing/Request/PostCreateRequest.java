package com.study.LikeLionKing.Request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    String title;
    String content;
    int postRole;
}
