package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.util.Date;

public class PostUpdate {

    @Getter
    public static class RequestDto{
        private String title;
        private String body;

        public Post toEntity(long id){
            Post post = Post.builder()
                    .id(id)
                    .title(title)
                    .content(body)
                    .build();
            return post;
        }
    }

}
