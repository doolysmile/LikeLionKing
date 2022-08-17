package com.hsy.likelion.LikeLionKing.member.dto;

import com.hsy.likelion.LikeLionKing.member.domain.Member;
import com.hsy.likelion.LikeLionKing.member.domain.MemberRole;
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
    private MemberRole role;       // 회원 종류

    // Member -> MemberDto로 변환(of)
    public static MemberReadDto toDto(Member member) {
        MemberReadDto memberReadDto = MemberReadDto.builder()
                .id(member.getId())
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
