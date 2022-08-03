package postsite.postsitespring.domain.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import postsite.postsitespring.common.entity.BaseEntity;

import java.sql.Timestamp;

@Getter
@Setter
public class Post extends BaseEntity {
    private String title;
    private String content;
    private byte isNotice; // true: 1, false: 0
    private int views;
    private int likes;
    private int postGroupId;

    @Builder
    private Post(Long id, Timestamp createdAt, Timestamp updatedAt, String title, String content, byte isNotice, int views, int likes, int postGroupId) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
        this.views = views;
        this.likes = likes;
        this.postGroupId = postGroupId;
    }
}
