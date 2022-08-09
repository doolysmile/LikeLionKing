package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

import java.util.Date;

public class PostUpdate {

    @Getter
    public static class RequestDto{
        private String title;
        private String body;

        public void updateEntity(Post post){
            post.setTitle(title);
            post.setContent(body);
        }
    }

}
