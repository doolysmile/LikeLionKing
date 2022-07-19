package com.hsy.likelion.LikeLionKing.dto;

import com.hsy.likelion.LikeLionKing.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private int role;       // 회원 종류

    public MemberDto(Long id, String loginId, String loginPw, int role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.role = role;
    }

    // MemberDto -> Member 변환
    public static Member toEntity(MemberDto memberDto) {
        Member member = Member.builder()
                .id(memberDto.getId())
                .loginId(memberDto.getLoginId())
                .loginPw(memberDto.getLoginPw())
                .build();
        return member;
    }

    // Member -> MemberDto로 변환(of)
    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getLoginId(), member.getLoginPw(), member.getRole());
    }
}
