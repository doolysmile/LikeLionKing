package com.hsy.likelion.LikeLionKing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Member {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private int role;       // 회원 종류

    public Member() {
    }

    public Member(Long id, String loginId, String loginPw, int role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.role = role;
    }
}
