package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;

import java.sql.Timestamp;

public class UserUpdate {

    @Getter
    public static class RequestDto{
        private String loginId;
        private String loginPw;
        private String nickname;

        public User toEntity(User user){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return User.builder()
                    .id(user.getId())
                    .loginId(loginId)
                    .loginPw(loginPw)
                    .nickname(user.getNickname())
                    .level(user.getLevel())
                    .role(user.getRole())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(timestamp)
                    .build();

        }
    }
}
