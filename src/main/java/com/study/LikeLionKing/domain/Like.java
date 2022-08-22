package com.study.LikeLionKing.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Like {
    long id;
    long userId;
    long postId;

    @Builder
    public Like(long id, long userId, long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }
}
