package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.dto.User;
import com.study.LikeLionKing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test/uc")
    String create(){
        User user = new User();
        user.setLoginId("u1");
        user.setLoginPw("u1");
        Long id = userService.save(user);
        System.out.println(userService.findById(id));
        return userService.findById(id).toString();
    }

    @GetMapping("/test/ur")
    String find(){
        User user  = userService.findById(1L).get();
        System.out.println(user);
        return user.toString();
    }
    @GetMapping("/test/ura")
    String findAll(){
        List<User> users  = userService.findAll();
        System.out.println(users);
        return users.toString();
    }

    @GetMapping("/test/uu")
    String update(){
        User user = userService.findById(1L).get();
        user.setLoginId("change");
        user.setLoginPw("change");
        userService.update(user);

        System.out.println(user.toString());
        return user.toString();
    }
    @GetMapping("/test/ud")
    String delete(){
        System.out.println(userService.findAll());
        userService.remove(1L);
        System.out.println(userService.findAll());
        return "delete";
    }
}

