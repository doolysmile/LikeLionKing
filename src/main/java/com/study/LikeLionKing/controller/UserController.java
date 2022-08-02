package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.request.UserCreateRequest;
import com.study.LikeLionKing.request.UserModifyRequest;
import com.study.LikeLionKing.domain.dto.UserDto;
import com.study.LikeLionKing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usr")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // 기능 코드
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserCreateRequest createRequest){
        UserDto userDto = UserDto.builder()
                .loginPw(createRequest.getLoginPw())
                .loginId(createRequest.getLoginId())
                .userRole(createRequest.getUserRole())
                .build();
        long id = userService.save(userDto);

        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<UserDto> retrieve(@RequestParam("id") Long id){
        System.out.println(id);
        UserDto userDto = userService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> retrieveAll(){
        List<UserDto> userDtoList = userService.findAll();
        return  ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserModifyRequest modifyRequest){
        UserDto userDto = userService.findById(modifyRequest.getId());

        System.out.println(userDto);
        userDto.setLoginPw(modifyRequest.getLoginPw());
        userDto.setLoginId(modifyRequest.getLoginId());
        userDto.setUserRole(modifyRequest.getUserRole());

        userService.update(userDto);

        System.out.println(userService.findById(modifyRequest.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/delete")
    public ResponseEntity<UserDto> delete(@RequestParam("id") Long id){
        userService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
}

