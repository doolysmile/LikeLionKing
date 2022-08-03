package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;

public class UserUpdate {

    @Getter
    public static class RequestDto{
        private String loginId;
        private String loginPw;
        private String nickname;

        public void updateEntity(User user){
            user.setLoginId(loginId);
            user.setLoginPw(loginPw);
            user.setNickname(loginPw);
        }
    }
}
