package com.kch.likelion.LikeLionKing.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserDto {
    private Long userSeq;
    private String loginId;
    private String nickName;

    public UserDto(User user){
        this.userSeq = user.getUserSeq();
        this.loginId = user.getLoginId();
        this.nickName = user.getNickName();
    }
}
