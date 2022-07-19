package com.hsy.likelion.LikeLionKing.dto.member;

import com.hsy.likelion.LikeLionKing.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberReadDto {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private String nickname;    // 닉네임
    private String email;   // email
    private String phone;   // 핸드폰 번호
    private int role;       // 회원 종류

    public MemberReadDto(String loginId, String loginPw, int role) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.role = role;
    }


    // Member -> MemberDto로 변환(of)
    public static MemberReadDto toDto(Member member) {
        MemberReadDto memberReadDto = MemberReadDto.builder()
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(member.getRole())
                .build();
        return memberReadDto;
    }
}
