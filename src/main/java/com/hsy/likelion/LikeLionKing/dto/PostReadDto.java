package com.hsy.likelion.LikeLionKing.dto;

import com.hsy.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostReadDto {
    private Long id;
    private String title;
    private String content;

    public PostReadDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Post -> PostCreateDto 변환
    public static PostReadDto toDto(Post post) {
        PostReadDto postCreateDto = PostReadDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        return postCreateDto;
    }
}
