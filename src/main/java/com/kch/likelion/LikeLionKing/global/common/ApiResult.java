package com.kch.likelion.LikeLionKing.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// TODO : 예제의 final의 이유
public class ApiResult<T> {
    private boolean success;
    private T response;
    private ApiError apiError;
}
