package com.hsy.likelion.LikeLionKing.dto.post;

import com.hsy.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostUpdateDto {
    private Long id;        // 게시글 id
    private String title;   // 게시글 제목
    private String content; // 게시글 내용

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
