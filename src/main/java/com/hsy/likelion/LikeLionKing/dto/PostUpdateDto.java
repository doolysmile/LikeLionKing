package com.hsy.likelion.LikeLionKing.dto;

import com.hsy.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostUpdateDto {
    private Long id;
    private String title;
    private String content;

    public PostUpdateDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // PostCreateDto -> Post 변환
    public static Post toEntity(PostUpdateDto postCreateDto) {
        Post post = Post.builder()
                .id(postCreateDto.getId())
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .build();
        return post;
    }
}
