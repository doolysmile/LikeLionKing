package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

public class PostUpdate {

    @Getter
    public static class RequestDto{
        private String title;
        private String content;

        public Post toEntity(){
            Post post = Post.builder()
                    .title(title)
                    .content(content)
                    .build();
            return post;
        }
    }

}
