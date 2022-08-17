package com.kch.likelion.LikeLionKing.domain.user;

import com.kch.likelion.LikeLionKing.domain.user.domain.User;
import com.kch.likelion.LikeLionKing.domain.user.domain.UserDto;
import com.kch.likelion.LikeLionKing.domain.user.domain.UserRequestDto;
import com.kch.likelion.LikeLionKing.global.error.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usr/member")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Object> joinUser(@RequestBody UserRequestDto userRequestDto){
        User newUser = userService.insert(userRequestDto.newUser());
        // TODO : user가 없을 떄 반환 생각하기
        if(newUser == null){
            System.out.println(" = ");
            return ResponseEntity.status(HttpStatus.OK).body(new DuplicateException("중복입니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(UserDto.toDto(newUser));
    }

    @PostMapping("/doLogin")
    public ResponseEntity<UserDto> doLogin(@RequestBody UserRequestDto userRequestDto){
        User findUser = userService.doLogin(userRequestDto.newUser());

// TODO : user가 없을 떄 반환 생각하기
        if(findUser == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(UserDto.toDto(findUser));
    }

}
