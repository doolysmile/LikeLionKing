package com.pbs.likelion.LikeLionKing.domain.dto;

import com.pbs.likelion.LikeLionKing.domain.Post;
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

    private int categoryId; // 게시글 종류
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    public PostDto(){}

    @Builder
    public PostDto(Long id, Long userId, int categoryId, String title, String content, Long views, Long recommended) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.views = views;
        this.recommended = recommended;
    }

    public static PostDto from(Post post){
        return new PostDto(post.getId(), post.getUserId(), post.getCategoryId(),post.getTitle(), post.getContent(), post.getViews(), post.getRecommended());
    }

    public Post toEntity(){
        Post post = Post.builder()
                .id(id)
                .userId(userId)
                .categoryId(categoryId)
                .title(title)
                .content(content)
                .views(views)
                .recommended(recommended)
                .build();
        return post;

    }

}
