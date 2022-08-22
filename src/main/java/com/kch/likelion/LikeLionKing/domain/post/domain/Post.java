package com.kch.likelion.LikeLionKing.domain.post.domain;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {

    private final Long postSeq;
    private Long userSeq;
    private int views;
    private int likes;
    private String title;
    private String content;
    private BoardType boardType;
    private final LocalDateTime createAt;

    public Post(String title, String content) {
        this(null, 0L, 0, 0, title, content, BoardType.NOTICE_BOARD, null);
    }
    // TODO : userSeq와 BoardType 초기값 설정 해준 것을 상황에 맞게 바꾸기
    public Post(Long postSeq, String title, String content) {
        this(postSeq, 0L, 0, 0, title, content, BoardType.FREE_BOARD, null);
    }
    public void increaseViews(){
        this.views++;
    }
    public Post(Long postSeq, Long userSeq, int views, int likes, String title, String content, BoardType boardType, LocalDateTime createAt) {
        this.postSeq = postSeq;
        this.userSeq = userSeq;
        this.views = views;
        this.likes = likes;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.createAt = LocalDateTime.now();
    }

    static public class Builder {
        private Long postSeq;
        private Long userSeq;
        private int views;
        private int likes;
        private String title;
        private String content;
        private BoardType boardType;
        private LocalDateTime createAt;

        public Builder() {

        }
        public Builder(Post post){
            this.postSeq = post.postSeq;
            this.userSeq = post.userSeq;
            this.views = post.views;
            this.likes = post.likes;
            this.title = post.title;
            this.content = post.content;
            this.boardType = post.boardType;
            this.createAt = post.createAt;
        }

        public Builder postSeq(Long postSeq) {
            this.postSeq = postSeq;
            return this;
        }
        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder views(int views) {
            this.views = views;
            return this;
        }

        public Builder likes(int views) {
            this.likes = likes;
            return this;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder content(String content) {
            this.content = content;
            return this;
        }
        public Builder boardType(BoardType boardType) {
            this.boardType = boardType;
            return this;
        }
        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }
        public Post build() {
            return new Post(postSeq, userSeq ,views, likes, title, content, boardType, createAt);
        }
    }
}