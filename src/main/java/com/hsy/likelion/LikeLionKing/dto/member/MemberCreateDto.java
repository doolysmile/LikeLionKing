package com.hsy.likelion.LikeLionKing.dto.member;

import com.hsy.likelion.LikeLionKing.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateDto {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private int role;       // 회원 종류

    public MemberCreateDto(Long id, String loginId, String loginPw, int role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.role = role;
    }

    // MemberDto -> Member 변환
    public static Member toEntity(MemberCreateDto memberDto) {
        // id는 DB에서 자동 생성되어 부여되기 떄문에 전달X
        Member member = Member.builder()
                .loginId(memberDto.getLoginId())
                .loginPw(memberDto.getLoginPw())
                .role(memberDto.getRole())
                .build();
        return member;
    }

    // Member -> MemberDto로 변환(of)
    public static MemberCreateDto toDto(Member member) {
        return new MemberCreateDto(member.getId(), member.getLoginId(), member.getLoginPw(), member.getRole());
    }
}
