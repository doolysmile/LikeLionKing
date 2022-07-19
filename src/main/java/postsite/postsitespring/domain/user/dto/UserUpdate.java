package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;

public class UserUpdate {

    @Getter
    public static class RequestDto{
        private long id;
        private String loginId;
        private String loginPw;
        private String nickname;

        public User toEntity(long id){
            User user = User.builder()
                    .id(id)
                    .loginId(loginId)
                    .loginPw(loginPw)
                    .nickname(nickname)
                    .build();
            return user;
        }
    }
}
