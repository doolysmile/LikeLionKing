package com.study.LikeLionKing.request;


import lombok.Getter;
import lombok.Setter;

@Getter
public class PostCreateRequest {
    String title;
    String content;
    int postRole=-1;
}
