package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.domain.Post;
import com.study.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@Controller
public class TestController {
    private final PostService postService;

    @Autowired
    public TestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/test/c")
    String create(){
        Post post = new Post();
        post.setTitle("asd");
        post.setContent("aqwe");
        Long id = postService.save(post);
        System.out.println(postService.findById(id));
        return postService.findById(id).toString();
    }
    @GetMapping("/test/r")
    String retrieve(){
        Post temp = postService.findById(1L).get();
        System.out.println(temp);
        return temp.toString();
    }
    @GetMapping("/test/ra")
    String retrieveAll(){
        List<Post> temp = postService.findAll();
        System.out.println(temp.toString());
        return temp.toString();
    }
    @GetMapping("/test/u")
    String update(){
        Post post = postService.findById(1L).get();
        post.setTitle("바꿈");
        post.setContent("바꿈 내용");
        postService.update(post);
        System.out.println(post.toString());
        return post.toString();
    }
    @GetMapping("/test/d")
    String delete(){
        System.out.println(postService.findAll());
        postService.delete(1L);
        System.out.println(postService.findAll());
        return "delete";
    }
}

