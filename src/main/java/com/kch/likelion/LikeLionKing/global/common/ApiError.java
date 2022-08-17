package com.kch.likelion.LikeLionKing.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// TODO : 예제의 final의 이유
public class ApiError {
    private String message;
    private int status;
}
