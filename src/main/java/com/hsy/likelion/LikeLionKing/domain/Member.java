package com.hsy.likelion.LikeLionKing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Member {
    private Long id;        // 회원 id(PK)
    private String loginId; // 로그인 id
    private String loginPw; // 로그인 password
    private String nickname;    // 닉네임
    private String email;   // email
    private String phone;   // 핸드폰 번호
//    private Integer role;       // 회원 종류
    private MemberRole role;    // 회원 종류

    public Member() {
    }

    public Member(Long id, String loginId, String loginPw, String nickname, String email, String phone, MemberRole role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}
