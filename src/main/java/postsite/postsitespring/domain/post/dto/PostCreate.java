package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.sql.Timestamp;

public class PostCreate {
    // private Long boardId;

    @Getter
    public static class RequestDto{
        private String title;
        private String body;
        private boolean isNotice;
        private int postGroupId;

        // dto -> entity
        public Post toEntity(){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Post post = Post.builder()
                    .title(title)
                    .content(body)
                    .isNotice(isNotice)
                    .postGroupId(postGroupId)
                    .views(0)
                    .likes(0)
                    .createdAt(timestamp)
                    .updatedAt(timestamp)
                    .build();
            return post;
        }
    }

    @Getter
    public static class ResponseDto{
        private long id;
        private String title;
        private String content;
        private boolean isNotice;
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
            this.isNotice = post.isNotice();
            this.views = post.getViews();
            this.likes = post.getLikes();
            this.postGroupId = post.getPostGroupId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
