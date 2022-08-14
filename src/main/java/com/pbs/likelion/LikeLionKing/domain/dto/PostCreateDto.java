package com.pbs.likelion.LikeLionKing.domain.dto;

import com.pbs.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostCreateDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt; // 생성날짜


    @Builder
    public PostCreateDto(Long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Post toEntity(PostCreateDto postCreateDto) {
        Post build = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .createdAt(postCreateDto.getCreatedAt())
                .build();

        return build;
    }

    public static PostCreateDto from(Post post){
        return new PostCreateDto(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt());
    }
}
