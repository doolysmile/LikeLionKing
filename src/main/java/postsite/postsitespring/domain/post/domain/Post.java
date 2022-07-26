package postsite.postsitespring.domain.post.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import postsite.postsitespring.common.entity.BaseEntity;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class Post extends BaseEntity {
    private String title;
    private String content;
    private boolean isNotice;
    private int views;
    private int likes;
}
