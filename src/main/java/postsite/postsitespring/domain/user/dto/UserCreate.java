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

         // dto => entity
         public User toEntity(){
             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
             User user = User.builder()
                     .loginId(loginId)
                     .loginPw(loginPw)
                     .nickname(nickname)
                     .level((short) 0)
                     .role(UserRoleTypeEnum.USER)
                     .createdAt(timestamp)
                     .updatedAt(timestamp)
                     .build();
             return user;
         }
     }

    @Getter
    public static class ResponseDto {
        private long id;
        private String loginId;
        private String loginPw;
        private String nickname;
        private short level;
        private UserRoleTypeEnum role;
        private Timestamp createdAt;
        private Timestamp updatedAt;

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
