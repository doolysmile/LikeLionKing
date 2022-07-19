package com.hsy.likelion.LikeLionKing.dto;

import com.hsy.likelion.LikeLionKing.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
    private Long id;        // 회원 id
    private String loginPw; // 회원 로그인 pw

    public MemberUpdateDto(Long id, String loginPw) {
        this.id = id;
        this.loginPw = loginPw;
    }

    // MemberDto -> Member 변환
    public static Member toEntity(MemberUpdateDto memberDto) {
        // id, pw만 전달(나머지는 변경 불가요소)
        Member member = Member.builder()
                .loginPw(memberDto.getLoginPw())
                .build();
        return member;
    }
}
