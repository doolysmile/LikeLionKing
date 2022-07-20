package com.study.LikeLionKing.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private Long id;
    private String loginId;
    private String loginPw;
    private int userRole;

    @Builder
    public User(Long id, String loginId, String loginPw, int userRole) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.userRole = userRole;
    }
}
