package com.study.LikeLionKing.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    Long id;
    String loginId;
    String loginPw;
}
