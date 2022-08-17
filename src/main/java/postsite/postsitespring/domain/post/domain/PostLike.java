package postsite.postsitespring.domain.post.domain;

import lombok.Builder;
import lombok.Getter;
import postsite.postsitespring.common.entity.BaseEntity;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.user.domain.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class PostLike{
    private final long postId;

    private final long userId;
    private final LocalDateTime createdAt;

    @Builder
    public PostLike(long postId, long userId, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
