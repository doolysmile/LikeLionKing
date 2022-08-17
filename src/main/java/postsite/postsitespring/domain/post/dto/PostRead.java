package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.sql.Timestamp;
import java.util.Date;

public class PostRead {
    @Getter
    public static class ResponseDto {
        private final long id;
        private final String title;
        private final String content;
        private final Boolean isNotice;
        private final int views;
        private final int postGroupId;
        private final Timestamp createdAt;
        private final Timestamp updatedAt;

        // entity -> dto
        public ResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.isNotice = post.getIsNotice() > 0;
            this.views = post.getViews();
            this.postGroupId = post.getPostGroupId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }

    }
}
