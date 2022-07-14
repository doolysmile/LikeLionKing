package com.kch.likelion.LikeLionKing.post.domain;


import lombok.Getter;

@Getter
public class Post {

    private final Long postSeq;

    private String title;

    private String content;

    public Post(String title, String content) {
        this(null, title, content);
    }
    public Post(Long postSeq, String title, String content) {
        this.postSeq = postSeq;
        this.title = title;
        this.content = content;
    }
    static public class Builder {
        private Long postSeq;
        private String title;
        private String content;

        public Builder() {
        }

        public Builder(Post post) {
            this.postSeq = post.postSeq;
            this.title= post.title;
            this.content = post.content;
        }

        public Builder postSeq(Long postSeq) {
            this.postSeq = postSeq;
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
            return new Post(postSeq, title, content);
        }
    }
}