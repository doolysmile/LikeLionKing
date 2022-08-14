package com.pbs.likelion.LikeLionKing.domain.dto;

import com.pbs.likelion.LikeLionKing.domain.Member;
import lombok.Getter;

@Getter
public class MemberReadDto {

    private String loginId;
    private String loginPwd;
    private int role; // 운영자 or 유저

    public MemberReadDto(String loginId, String loginPwd, int role) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public static MemberReadDto from(Member member){
        return new MemberReadDto(member.getLoginId(), member.getLoginPwd(), member.getRole());
    }
}
