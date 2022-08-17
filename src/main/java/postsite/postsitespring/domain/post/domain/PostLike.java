package postsite.postsitespring.domain.post.domain;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostLike{
    private final Long postId;

    private final Long userId;
    private final LocalDateTime createdAt;

    @Builder
    public PostLike(long postId, long userId, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
