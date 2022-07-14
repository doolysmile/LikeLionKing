package com.kch.likelion.LikeLionKing.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private boolean success;
    private String response;
}
