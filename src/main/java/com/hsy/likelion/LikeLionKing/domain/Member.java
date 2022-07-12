package com.hsy.likelion.LikeLionKing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    @JsonProperty("id")
    private Long id;        // 회원 id
    @JsonProperty("login_id")
    private String loginId; // 회원 로그인 id
    @JsonProperty("login_pw")
    private String loginPw; // 회원 로그인 pw
}
