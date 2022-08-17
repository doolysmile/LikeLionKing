package com.study.LikeLionKing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Like {
    long id;
    long userId;
    long postId;
}
