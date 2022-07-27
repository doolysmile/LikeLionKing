package lionStudy.lionStudy.domain.DTO;

import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDto {

    private Long id; // 식별자
    private Long userId; // 작성자 ID, 외래키
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    public PostDto(){}

    @Builder
    public PostDto(Long id, Long userId, String title, String content, Long views, Long recommended) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.views = views;
        this.recommended = recommended;
    }

    public static PostDto from(Post post){
        return new PostDto(post.getId(), post.getUserId(), post.getTitle(), post.getContent(), post.getViews(), post.getRecommended());
    }

    public Post toEntity(){
        Post post = new Post.Builder(id, userId, title, content, views, recommended).build();
        return post;

    }

}
