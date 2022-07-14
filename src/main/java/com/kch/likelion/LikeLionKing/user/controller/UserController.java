package com.kch.likelion.LikeLionKing.user.controller;

import com.kch.likelion.LikeLionKing.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

}
