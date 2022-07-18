package postsite.postsitespring.domain.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;

    public Post(){

    }
    public Post(Long id,String title,String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
