package postsite.postsitespring.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.sql.Timestamp;

public class PostCreate {
    // private Long boardId;

    @Getter
    public static class RequestDto{
        private String title;
        private String body;
        // is를 붙인 변수를 사용하려면 boolean => Boolean 사용하자.
        private Boolean isNotice;
        private int postGroupId;

        // dto -> entity
        public Post toEntity(){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return Post.builder()
                    .title(title)
                    .content(body)
                    .isNotice((byte) (isNotice? 1 : 0))
                    .postGroupId(postGroupId)
                    .views(0)
                    .likes(0)
                    .createdAt(timestamp)
                    .updatedAt(timestamp)
                    .build();
        }
    }

    @Getter
    public static class ResponseDto{
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
