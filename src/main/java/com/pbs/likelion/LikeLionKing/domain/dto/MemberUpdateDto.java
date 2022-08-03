package com.pbs.likelion.LikeLionKing.domain.dto;

import com.pbs.likelion.LikeLionKing.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberUpdateDto {
    private Long id;
    private String loginPwd;

    public static Member toEntity(MemberUpdateDto memberUpdateDto) {
        Member member = Member.builder()
                .id(memberUpdateDto.getId())
                .loginPwd(memberUpdateDto.getLoginPwd())
                .build();
        return member;
    }

}
