package com.kch.likelion.LikeLionKing.domain.test;

import com.kch.likelion.LikeLionKing.domain.post.domain.PostDto;
import com.kch.likelion.LikeLionKing.domain.user.domain.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping("/test1")
    public List<PostDto> test1() {

        return null;
    }

    @GetMapping("/test2")
    public List<UserDto> test2() {

        return null;

    }
}
