package com.study.LikeLionKing.Request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String loginId;
    private String loginPw;
    private int userRole;
}
