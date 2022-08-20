package com.hsy.likelion.LikeLionKing.post.dto;

import com.hsy.likelion.LikeLionKing.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostReadDto {
    private Long id;        // 게시글 id
    private Long memberId;  // 작성자 id(인덱스)
    private Integer categoryId; // 게시글 카테고리 id
    private String title;   // 게시글 제목
    private String content; // 게시글 내용
    private String createdAt; // 게시물 생성 일시
    private String updatedAt;    // 게시물 최종 수정 일시
    private Long views;     // 조회수
    private Long likes;     // 좋아요 수

    // Post -> PostCreateDto 변환
    public static PostReadDto toDto(Post post) {
        // updatedAt는 전송X
        PostReadDto postCreateDto = PostReadDto.builder()
                .id(post.getId())
                .memberId(post.getMemberId())
                .categoryId(post.getCategoryId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .views(post.getViews())
                .likes(post.getLikes())
                .build();
        return postCreateDto;
    }
}
