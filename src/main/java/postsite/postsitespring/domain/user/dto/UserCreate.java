package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.domain.UserRoleTypeEnum;

import java.sql.Timestamp;

public class UserCreate {

     @Getter
     public static class RequestDto{
         private String loginId;
         private String loginPw;
         private String nickname;

         // DTO -> Entity
         public User toEntity(){
             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
             return  User.builder()
                     .loginId(loginId)
                     .loginPw(loginPw)
                     .nickname(nickname)
                     .level((short) 0)
                     .role(UserRoleTypeEnum.USER)
                     .createdAt(timestamp)
                     .updatedAt(timestamp)
                     .build();
         }
     }

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

        public ResponseDto(long id, User user){
            this.id = id;
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
