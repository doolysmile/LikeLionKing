package com.hsy.likelion.LikeLionKing.controller;

import com.hsy.likelion.LikeLionKing.domain.Post;
import com.hsy.likelion.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// @RestController = @Controller + @ResponseBody
// Controller로 데이터 반환하기(JSON 형태의 객체 데이터반환에 주로 사용)
@RestController
@RequestMapping("/usr/article")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/doWrite")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
//        Post post = new Post();
//        post.setTitle(title);
//        post.setContent(content);
        return ResponseEntity.status(HttpStatus.OK).body(postService.save(post));
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<Post>> getDetailPost(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAll());
    }

    @PutMapping("/doModify")
    public Post modifyPost(@RequestBody Post post) {
//        Post post = new Post();
//        post.setId(id);
//        post.setTitle(title);
//        post.setContent(content);
        postService.update(post);
        return post;
    }

    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return postId;
    }
}
