package com.kch.likelion.LikeLionKing.user.domain;

import com.kch.likelion.LikeLionKing.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long userSeq;

    private String loginId;
    private String loginPw;
    private String nickName;
    private UserRole userRole;


    public User(String loginId, String loginPw, String nickName){
        this(null, loginId, loginPw, nickName, UserRole.NORMAL_USER);
    }
    public User(Long userSeq, String loginId, String loginPw, String nickName){
        this(userSeq, loginId, loginPw, nickName, UserRole.NORMAL_USER);
    }
    public User(Long userSeq, String loginId, String loginPw, String nickName, UserRole userRole){
        this.userSeq = userSeq;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickName = nickName;
        this.userRole = userRole;
    }

    static public class Builder {
        private Long userSeq;
        private String loginId;
        private String loginPw;

        private String nickName;
        private UserRole userRole;
        public Builder(){

        }

        public Builder(User user){
            this.userSeq = user.userSeq;
            this.loginId = user.getLoginId();
            this.loginPw = user.getLoginPw();
            this.nickName = user.getNickName();
            this.userRole = user.getUserRole();
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
        public Builder nickName(String nickName){
            this.nickName = nickName;
            return this;
        }
        public Builder userRole(UserRole userRole){
            this.userRole = userRole;
            return this;
        }
        public User build() {
            return new User(userSeq, loginId, loginPw, nickName, userRole);
        }
    }

}
