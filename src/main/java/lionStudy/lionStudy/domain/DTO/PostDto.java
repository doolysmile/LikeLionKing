package lionStudy.lionStudy.domain.DTO;

import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private Long id; // 식별자
    private Long userId; // 작성자 ID, 외래키
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    public PostDto(){}
    @Builder
    public PostDto(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.recommended = post.getRecommended();
    }

    public Post toEntity(){
        Post post = new Post.Builder(id, userId, title, content, views, recommended).build();

        return post;

    }

}
