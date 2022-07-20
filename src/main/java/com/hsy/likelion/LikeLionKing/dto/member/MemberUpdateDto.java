package com.hsy.likelion.LikeLionKing.dto.member;

import com.hsy.likelion.LikeLionKing.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private String nickname;    // 닉네임
    private String email;   // email
    private String phone;   // 핸드폰 번호

    public MemberUpdateDto(Long id, String loginPw) {
        this.id = id;
        this.loginPw = loginPw;
    }

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
