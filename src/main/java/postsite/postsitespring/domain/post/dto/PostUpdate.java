package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.sql.Timestamp;
import java.util.Date;

public class PostUpdate {

    @Getter
    public static class RequestDto{
        private String title;
        private String body;

        public Post toEntity(Post post){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return Post.builder()
                    .id(post.getId())
                    .title(title)
                    .content(body)
                    .isNotice(post.getIsNotice())
                    .views(post.getViews())
                    .postGroupId(post.getPostGroupId())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(timestamp)
                    .build();
        }
    }

}
