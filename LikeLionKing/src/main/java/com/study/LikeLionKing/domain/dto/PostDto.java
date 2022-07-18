package com.study.LikeLionKing.domain.dto;

import com.study.LikeLionKing.domain.Post;
import lombok.Builder;

public class PostDto {

    private Long id;
    private Long memberId; //작성자 id
    private String written; // 작성 시간
    private String lastModified; //수정시간
    private Long views; // 조회수
    private Long recommended; //추천수
    private String title; //제목
    private String content; // 내용
    private int postRole; // 1일때 공지사항 2일 때 자유게시판

    Post toEntity(){
        Post post = Post.builder()
                .id(id)
                .memberId(memberId)
                .written(written)
                .lastModified(lastModified)
                .views(views)
                .recommended(recommended)
                .title(title)
                .content(content)
                .postRole(postRole)
                .build();
        return post;
    }

    @Builder

    public PostDto(Long id, Long memberId, String written, String lastModified, Long views, Long recommended, String title, String content, int postRole) {
        this.id = id;
        this.memberId = memberId;
        this.written = written;
        this.lastModified = lastModified;
        this.views = views;
        this.recommended = recommended;
        this.title = title;
        this.content = content;
        this.postRole = postRole;
    }
}

