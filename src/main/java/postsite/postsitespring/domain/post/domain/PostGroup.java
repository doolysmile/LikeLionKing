package postsite.postsitespring.domain.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import postsite.postsitespring.common.entity.BaseEntity;

import java.sql.Timestamp;


@Getter
@Setter
public class PostGroup extends BaseEntity {
    private String name;

    @Builder
    private PostGroup(Long id, Timestamp createdAt, Timestamp updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }
}
