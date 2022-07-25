package com.hsy.likelion.LikeLionKing.controller;

import com.hsy.likelion.LikeLionKing.domain.Post;
import com.hsy.likelion.LikeLionKing.dto.post.PostCreateDto;
import com.hsy.likelion.LikeLionKing.dto.post.PostReadDto;
import com.hsy.likelion.LikeLionKing.dto.post.PostUpdateDto;
import com.hsy.likelion.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<PostCreateDto> createPost(@RequestBody PostCreateDto postCreateDto) {
        Post post = PostCreateDto.toEntity(postCreateDto);
        Post postCreated = postService.save(post);
        return ResponseEntity.status(HttpStatus.OK).body(PostCreateDto.toDto(postCreated));
    }

    @GetMapping("/detail")
    public ResponseEntity<PostReadDto> getDetailPost(@RequestParam("id") Long id) {
        Post post = postService.findById(id).orElse(null);// 없으면 null로 반환
        // null 처리 추가구현 필요
        return ResponseEntity.status(HttpStatus.OK).body(PostReadDto.toDto(post));
    }

    @GetMapping
    public ResponseEntity<List<PostReadDto>> getPosts() {
        // List의 Post를 모두 PostReadDto로 변환하여 List로 반환
        List<PostReadDto> postReadDtos = postService.findAll().stream()
                .map(p -> PostReadDto.toDto(p))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(postReadDtos);
    }

    @PutMapping("/doModify")
    public void updatePost(@RequestBody PostUpdateDto postUpdateDto) {
        Post post = PostUpdateDto.toEntity(postUpdateDto);
        postService.update(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.delete(postId);
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
