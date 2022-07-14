package com.kch.likelion.LikeLionKing.user.controller;

import com.kch.likelion.LikeLionKing.post.domain.Post;
import com.kch.likelion.LikeLionKing.post.domain.PostRequestDto;
import com.kch.likelion.LikeLionKing.post.domain.PostResponseDto;
import com.kch.likelion.LikeLionKing.user.domain.User;
import com.kch.likelion.LikeLionKing.user.domain.UserRequestDto;
import com.kch.likelion.LikeLionKing.user.domain.UserResponseDto;
import com.kch.likelion.LikeLionKing.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping("join")
    public ResponseEntity<Object> joinUser(@RequestBody UserRequestDto userRequestDto){


        User newUser = userService.insert(userRequestDto.newUser());

        if(newUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(true, "가입완료"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(false, "가입실패"));
    }

}
