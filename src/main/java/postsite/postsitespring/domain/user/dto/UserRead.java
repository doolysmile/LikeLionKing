package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.domain.UserRoleTypeEnum;

import java.sql.Timestamp;

public class UserRead {

    @Getter
    public static class ResponseDto {
        private final long id;
        private final String loginId;
        private final String loginPw;
        private final String nickname;
        private final short level;
        private final UserRoleTypeEnum role;
        private final Timestamp createdAt;
        private final Timestamp updatedAt;

        public ResponseDto(User user){
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.loginPw = user.getLoginPw();
            this.nickname = user.getNickname();
            this.level = user.getLevel();
            this.role = user.getRole();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
        }
    }
}
