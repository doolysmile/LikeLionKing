package com.study.LikeLionKing.Request;

import lombok.Getter;

@Getter
public class UserModifyRequest {
    private String loginId;
    private String loginPw;
    private int userRole;
}
