package com.hsy.likelion.LikeLionKing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw

    public MemberDto(Long id, String loginId, String loginPw) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
