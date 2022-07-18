package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;

public class UserUpdate {

    @Getter
    public static class RequestDto{
        private long id;
        private String loginId;
        private String loginPw;

        public User toEntity(){
            User user = User.builder()
                    .loginId(loginId)
                    .loginPw(loginPw)
                    .build();
            return user;
        }
    }
}
