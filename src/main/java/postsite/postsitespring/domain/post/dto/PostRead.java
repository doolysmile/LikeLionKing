package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.sql.Timestamp;
import java.util.Date;

public class PostRead {
    // private Long boardId;

    @Getter
    public static class ResponseDto {
        private long id;
        private String title;
        private String content;
        private Boolean isNotice;
        private int views;
        private int likes;
        private int postGroupId;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        // entity -> dto
        public ResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.isNotice = post.getIsNotice() > 0;
            this.views = post.getViews();
            this.likes = post.getLikes();
            this.postGroupId = post.getPostGroupId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }

    }
}
