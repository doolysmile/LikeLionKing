package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.domain.Post;
import com.study.LikeLionKing.domain.dto.PostDto;
import com.study.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/test/c")
    String create(){
        PostDto postDto = new PostDto();
        postDto.setTitle("asd");
        postDto.setContent("aqwe");
        Long id = postService.save(postDto);
        System.out.println(postService.findById(id));
        return postService.findById(id).toString();
    }
    @GetMapping("/test/r")
    String retrieve(){
        PostDto temp = postService.findById(1L).get();
        System.out.println(temp);
        return temp.toString();
    }
    // TODO 오류발생
    @GetMapping("/test/ra")
    String retrieveAll(){
        List<PostDto> temp = postService.findAll();
        System.out.println(temp.toString());
        return temp.toString();
    }
    @GetMapping("/test/u")
    String update(){
        PostDto postDto = postService.findById(1L).get();
        postDto.setTitle("바꿈");
        postDto.setContent("바꿈 내용");
        postService.update(postDto);
        System.out.println(postDto.toString());
        return postDto.toString();
    }
    @GetMapping("/test/d")
    String delete(){
        System.out.println(postService.findAll());
        postService.delete(1L);
        System.out.println(postService.findAll());
        return "delete";
    }
}

