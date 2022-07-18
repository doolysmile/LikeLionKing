package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;

public class UserSave {

    @Getter
    public static class ResponseDto {
        private long id;
        private String loginId;
        private String loginPw;

        public ResponseDto(User user){
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.loginPw = user.getLoginPw();
        }
    }
}
