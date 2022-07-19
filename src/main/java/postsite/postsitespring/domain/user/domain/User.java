package postsite.postsitespring.domain.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import postsite.postsitespring.common.entity.BaseEntity;

@Getter
@Setter
@SuperBuilder
public class User extends BaseEntity {
    private String loginId;
    private String loginPw;
    private String nickname;
    private short level;
    private UserRoleTypeEnum role;
}

