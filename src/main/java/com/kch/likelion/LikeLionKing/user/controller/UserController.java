package com.kch.likelion.LikeLionKing.user.controller;

import com.kch.likelion.LikeLionKing.post.domain.Post;
import com.kch.likelion.LikeLionKing.post.domain.PostRequestDto;
import com.kch.likelion.LikeLionKing.post.domain.PostResponseDto;
import com.kch.likelion.LikeLionKing.user.domain.User;
import com.kch.likelion.LikeLionKing.user.domain.UserDto;
import com.kch.likelion.LikeLionKing.user.domain.UserRequestDto;
import com.kch.likelion.LikeLionKing.user.domain.UserResponseDto;
import com.kch.likelion.LikeLionKing.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usr/member")
public class UserController {

    private final UserService userService;

//    @GetMapping("/login")
//    public ResponseEntity<Object> loginForm(){
//
//    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> joinUser(@RequestBody UserRequestDto userRequestDto){

        User newUser = userService.insert(userRequestDto.newUser());
        // TODO : user가 없을 떄 반환 생각하기
        if(newUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UserDto(newUser));
    }

    @PostMapping("/doLogin")
    public ResponseEntity<UserDto> doLogin(@RequestBody UserRequestDto userRequestDto){
        User findUser = userService.doLogin(userRequestDto.newUser());

// TODO : user가 없을 떄 반환 생각하기
        if(findUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UserDto(findUser));
    }

}
