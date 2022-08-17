package com.study.LikeLionKing.domain;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {
    private Long id;
    private Long memberId; //작성자 id
    private String written; // 작성 시간
    private String lastModified; //수정시간
    private Long views; // 조회수
    private Long recommended;//추천수
    private String title; //제목
    private String content; // 내용
    private int postRole; // 1일때 공지사항 2일 때 자유게시판


    @Builder
    public Post(Long id, Long memberId, String written, String lastModified, Long views, Long recommended, String title, String content, int postRole) {
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
