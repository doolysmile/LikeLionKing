package com.hsy.likelion.LikeLionKing.dto.post;

import com.hsy.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostCreateDto {

    private Long id;        // 게시글 id
    private Long memberId;  // 작성자 id
    private Integer categoryId; // 게시글 카테고리 id
    private String title;   // 게시글 제목
    private String content; // 게시글 내용
    private String created; // 게시물 생성 일시
    private String lastUpdated;    // 게시물 최종 수정 일시
    private Long views;     // 조회수
    private Long likes;     // 좋아요 수

    public PostCreateDto(Long id, Long memberId, Integer categoryId, String title, String content, String created, String lastUpdated, Long views, Long likes) {
        this.id = id;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.views = views;
        this.likes = likes;
    }

    // PostCreateDto -> Post 변환
    public static Post toEntity(PostCreateDto postCreateDto) {
        // id는 DB에서 자동 생성되어 부여되기 떄문에 전달X
        Post post = Post.builder()
                .memberId(postCreateDto.getMemberId())
                .categoryId(postCreateDto.getCategoryId())
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .created(postCreateDto.getCreated())
                .lastUpdated(postCreateDto.getLastUpdated())
                .views(postCreateDto.getViews())
                .likes(postCreateDto.getLikes())
                .build();
        return post;
    }

    // Post -> PostCreateDto 변환
    public static PostCreateDto toDto(Post post) {
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .id(post.getId())
                .memberId(post.getMemberId())
                .categoryId(post.getCategoryId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .lastUpdated(post.getLastUpdated())
                .views(post.getViews())
                .likes(post.getLikes())
                .build();
        return postCreateDto;
    }
}
