package lionStudy.lionStudy.domain.DTO;

import lionStudy.lionStudy.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PostCreateDto {
    private Long id;
    private String title;
    private String content;

    @Builder
    public PostCreateDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Post toEntity(PostCreateDto postCreateDto) {
        Post build = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .build();
        return build;
    }
}
