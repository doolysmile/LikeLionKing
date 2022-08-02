package com.study.LikeLionKing.Request;

import lombok.Getter;

@Getter
public class UserModifyRequest {
    private long id;
    private String loginId;
    private String loginPw;
    private int userRole;
}
