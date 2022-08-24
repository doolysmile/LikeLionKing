package postsite.postsitespring.domain.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import postsite.postsitespring.common.entity.BaseEntity;

import java.sql.Timestamp;

@Getter
public class User extends BaseEntity {
    private final String loginId;
    private final String loginPw;
    private final String nickname;
    private final short level;
    private final UserRoleTypeEnum role;

    @Builder
    private User(Long id, Timestamp createdAt, Timestamp updatedAt, String loginId, String loginPw, String nickname, short level, UserRoleTypeEnum role) {
        super(id, createdAt, updatedAt);
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickname = nickname;
        this.level = level;
        this.role = role;
    }
}

