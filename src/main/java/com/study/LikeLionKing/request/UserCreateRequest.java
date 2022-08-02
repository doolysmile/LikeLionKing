package com.study.LikeLionKing.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String loginId;
    private String loginPw;
    private int userRole;
}
