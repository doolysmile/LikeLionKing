package com.hsy.likelion.LikeLionKing.dto;

import com.hsy.likelion.LikeLionKing.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberReadDto {
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private int role;       // 회원 종류

    public MemberReadDto(String loginId, String loginPw, int role) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.role = role;
    }


    // Member -> MemberDto로 변환(of)
    public static MemberReadDto toDto(Member member) {
        return new MemberReadDto(member.getLoginId(), member.getLoginPw(), member.getRole());
    }
}
