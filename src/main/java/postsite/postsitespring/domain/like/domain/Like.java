package postsite.postsitespring.domain.like.domain;

import lombok.Builder;
import lombok.Getter;
import postsite.postsitespring.common.entity.BaseEntity;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.user.domain.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class Like{
    private final int id;
    private final Post post;
    private final User user;

    private final LocalDateTime createdAt;

    @Builder
    private Like(int id, Post post, User user, LocalDateTime createdAt) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.createdAt = createdAt;
    }
}
