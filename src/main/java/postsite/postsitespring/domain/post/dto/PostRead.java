package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.util.Date;

public class PostRead {
    // private Long boardId;

    @Getter
    public static class ResponseDto{
        private long id;
        private String title;
        private String content;
        private boolean isNotice;
        private int views;
        private int likes;
        private Date createdAt;
        private Date updatedAt;

        // entity -> dto
        public ResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.isNotice = post.isNotice();
            this.views = post.getViews();
            this.likes = post.getLikes();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
