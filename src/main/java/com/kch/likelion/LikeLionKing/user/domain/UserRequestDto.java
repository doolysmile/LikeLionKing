package com.kch.likelion.LikeLionKing.user.domain;

import com.kch.likelion.LikeLionKing.post.domain.Post;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private Long userSeq;
    private String loginId;
    private String loginPw;

    private String nickName;
    public User newUser(){
        if(userSeq != null){
            return new User(this.loginId, this.loginPw, this.nickName);
        }
        return new User(this.userSeq, this.loginId, this.loginPw, this.nickName);
    }
}
