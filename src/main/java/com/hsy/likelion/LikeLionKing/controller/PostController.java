package com.hsy.likelion.LikeLionKing.controller;

import com.hsy.likelion.LikeLionKing.domain.Post;
import com.hsy.likelion.LikeLionKing.dto.PostDto;
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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        // postDto -> post로 변환
        Post post = convertToEntity(postDto);
        Post postCreated = postService.save(post);
        // post -> posDto로 변환하여 반환
        return ResponseEntity.status(HttpStatus.OK).body(convertToDto(postCreated));
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
    public void updatePost(@RequestBody PostDto postDto) {
        // postDto -> post로 변환
        Post post = convertToEntity(postDto);
        postService.update(post);
    }

    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return postId;
    }

    private Post convertToEntity(PostDto postDto) {
        return new Post(postDto.getId(), postDto.getTitle(), postDto.getTitle());
    }

    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent());
    }

//    private Post convertToEntity(PostDto postDto) throws ParseException {
//        Post post = modelMapper.map(postDto, Post.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (postDto.getId() != null) {
//            Post oldPost = postService.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
//        return post;
//    }
}
