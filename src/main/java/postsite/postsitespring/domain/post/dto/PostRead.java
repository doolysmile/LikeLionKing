package postsite.postsitespring.domain.post.dto;

import lombok.Getter;
import postsite.postsitespring.domain.post.domain.Post;

public class PostRead {
    // private Long boardId;

    @Getter
    public static class ResponseDto{
        private long id;
        private String title;
        private String content;

        // entity -> dto
        public ResponseDto(Post post){
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
        }
    }
}
