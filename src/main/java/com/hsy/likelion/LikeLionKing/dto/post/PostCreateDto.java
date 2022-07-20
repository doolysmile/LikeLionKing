package com.hsy.likelion.LikeLionKing.dto.post;

import com.hsy.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostCreateDto {
    private Long id;
    private String title;
    private String content;

    public PostCreateDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // PostCreateDto -> Post 변환
    public static Post toEntity(PostCreateDto postCreateDto) {
        // id는 DB에서 자동 생성되어 부여되기 떄문에 전달X
        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .build();
        return post;
    }

    // Post -> PostCreateDto 변환
    public static PostCreateDto toDto(Post post) {
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        return postCreateDto;
    }
}
