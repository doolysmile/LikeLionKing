package postsite.postsitespring.domain.user.dto;

import lombok.Getter;
import postsite.postsitespring.domain.user.domain.User;

public class UserCreate {

     @Getter
     public static class RequestDto{
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
