package com.kch.likelion.LikeLionKing.user.domain;

import com.kch.likelion.LikeLionKing.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long userSeq;

    private String loginId;
    private String loginPw;

    public User(String loginId, String loginPw){
        this(null, loginId, loginPw);
    }

    public User(Long userSeq, String loginId, String loginPw){
        this.userSeq = userSeq;
        this.loginId = loginId;
        this.loginId = loginPw;
    }

    static public class Builder {
        private Long userSeq;
        private String loginId;
        private String loginPw;

        public Builder(){

        }

        public Builder(User user){
            this.userSeq = user.userSeq;
            this.loginId = user.getLoginId();
            this.loginPw = user.getLoginPw();
        }

        public Builder userSeq(Long userSeq){
            this.userSeq = userSeq;
            return this;
        }

        public Builder loginId(String loginId){
            this.loginId = loginId;
            return this;
        }
        public Builder loginPw(String loginPw){
            this.loginPw = loginPw;
            return this;
        }
        public User build() {
            return new User(userSeq, loginId, loginPw);
        }
    }

}
