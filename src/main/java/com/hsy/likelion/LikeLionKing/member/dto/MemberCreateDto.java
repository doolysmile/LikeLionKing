package com.hsy.likelion.LikeLionKing.member.dto;

import com.hsy.likelion.LikeLionKing.member.domain.Member;
import com.hsy.likelion.LikeLionKing.member.domain.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberCreateDto {
    private Long id;        // 회원 id
    private String loginId; // 회원 로그인 id
    private String loginPw; // 회원 로그인 pw
    private String nickname;    // 닉네임
    private String email;   // email
    private String phone;   // 핸드폰 번호
    private MemberRole role;       // 회원 종류

    // MemberDto -> Member 변환
    public static Member toEntity(MemberCreateDto memberCreateDto) {
        // id는 DB에서 자동 생성되어 부여되기 떄문에 전달X
        Member member = Member.builder()
                .loginId(memberCreateDto.getLoginId())
                .loginPw(memberCreateDto.getLoginPw())
                .nickname(memberCreateDto.getNickname())
                .email(memberCreateDto.getEmail())
                .phone(memberCreateDto.getPhone())
                .role(memberCreateDto.getRole())
                .build();
        return member;
    }

    // Member -> MemberDto로 변환(of)
    public static MemberCreateDto toDto(Member member) {
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(member.getRole())
                .build();
        return memberCreateDto;
    }
}
