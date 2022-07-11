package com.kch.likelion.LikeLionKing.post.domain;


import lombok.Getter;

@Getter
public class Post {

    private final Long seq;

    private String title;

    private String content;

    public Post(String title, String content) {
        this(null, title, content);
    }
    public Post(Long seq, String title, String content) {
        this.seq = seq;
        this.title = title;
        this.content = content;
    }
    static public class Builder {
        private Long seq;
        private String title;
        private String content;

        public Builder() {
        }

        public Builder(Post post) {
            this.seq = post.seq;
            this.title= post.title;
            this.content = post.content;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
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


        public Post build() {
            return new Post(seq, title, content);
        }
    }
}