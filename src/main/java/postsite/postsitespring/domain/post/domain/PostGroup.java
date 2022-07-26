package postsite.postsitespring.domain.post.domain;

import lombok.Getter;
import lombok.Setter;
import postsite.postsitespring.common.entity.BaseEntity;


@Getter
@Setter
public class PostGroup extends BaseEntity {
    private String name;
}
