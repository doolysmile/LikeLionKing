package com.study.LikeLionKing.domain.dto;

import com.study.LikeLionKing.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String loginId;
    private String loginPw;
    private int userRole;

    public User toEntity(){
        User user = User.builder()
                .id(id)
                .loginId(loginId)
                .loginPw(loginPw)
                .userRole(userRole)
                .build();
        return user;
    }

    @Builder
    public UserDto(Long id, String loginId, String loginPw, int userRole) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.userRole = userRole;
    }
}
