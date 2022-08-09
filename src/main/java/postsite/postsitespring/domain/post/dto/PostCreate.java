package postsite.postsitespring.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.sql.Timestamp;

public class PostCreate {
    @Getter
    public static class RequestDto{
        private String title;
        private String body;
        // is를 붙인 변수를 사용하려면 boolean => Boolean 사용하자.
        private Boolean isNotice;
        private int postGroupId;

        // DTO -> Entity
        public Post toEntity(){
            if(isNotice == null) isNotice = false;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return Post.builder()
                    .title(title)
                    .content(body)
                    .isNotice((byte) (isNotice? 1 : 0))
                    .views(0)
                    .likes(0)
                    .postGroupId(postGroupId == 0 ? 2 : postGroupId)
                    .createdAt(timestamp)
                    .updatedAt(timestamp)
                    .build();
        }
    }

    @Getter
    public static class ResponseDto{
        private final long id;
        private final String title;
        private final String content;
        private final Boolean isNotice;
        private final int views;
        private final int likes;
        private final int postGroupId;
        private final Timestamp createdAt;
        private final Timestamp updatedAt;
        // entity -> dto
        public ResponseDto(long id, Post post) {
            this.id = id;
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
