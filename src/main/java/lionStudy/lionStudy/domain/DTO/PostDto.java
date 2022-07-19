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
    private Long userId; // 작성자 ID
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    @Builder
    public PostDto(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.recommended = post.getRecommended();
    }

//    public Post toEntity(){
//        Post post = Post.builder()
//                .id(id)
//                .userId(userId)
//                .title(title)
//                .content(content)
//                .views(views)
//                .recommended(recommended)
//                .build();
//
//        return post;
//
//    }

}
