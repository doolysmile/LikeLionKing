package postsite.postsitespring.domain.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String loginId;
    private String loginPw;

    public User(){

    }
    public User(Long id, String loginId, String loginPw) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
