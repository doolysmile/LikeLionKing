package com.hsy.likelion.LikeLionKing.member.dto;

import com.hsy.likelion.LikeLionKing.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
    private Long id;        // 회원 id
    private String loginPw; // 회원 로그인 pw
    private String nickname;    // 닉네임
    private String email;   // email
    private String phone;   // 핸드폰 번호

    // MemberDto -> Member 변환
    public static Member toEntity(MemberUpdateDto memberUpdateDto) {
        // id, role은 변경 불가
        Member member = Member.builder()
                .id(memberUpdateDto.getId())
                .loginPw(memberUpdateDto.getLoginPw())
                .nickname(memberUpdateDto.getNickname())
                .email(memberUpdateDto.getEmail())
                .phone(memberUpdateDto.getPhone())
                .build();
        return member;
    }
}
