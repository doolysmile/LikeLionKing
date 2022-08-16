package com.study.LikeLionKing.request;

import lombok.Getter;

@Getter
public class UserModifyRequest {
    private long id;
    private String loginId;
    private String loginPw;
    private int userRole;
}
