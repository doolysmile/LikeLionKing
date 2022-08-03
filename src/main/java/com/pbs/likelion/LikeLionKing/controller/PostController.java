package com.pbs.likelion.LikeLionKing.controller;

import com.pbs.likelion.LikeLionKing.domain.dto.PostUpdateDto;
import com.pbs.likelion.LikeLionKing.service.PostService;
import com.pbs.likelion.LikeLionKing.domain.dto.PostCreateDto;
import com.pbs.likelion.LikeLionKing.domain.dto.PostDto;
import com.pbs.likelion.LikeLionKing.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/usr/article")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/detail")
    public ResponseEntity<PostDto> getDetail(@RequestParam("id") Long id) {
        Post post = postService.findOne(id).orElse(null); // Optional -> 없으면 null값 반환
        return ResponseEntity.status(HttpStatus.OK).body(PostDto.from(post));
    }

    /**
     * 수정폼 -> id, 제목, 타이틀만 표시되도록 가정
     */
    @GetMapping("/modify")
    public ResponseEntity<PostCreateDto> getModify(@RequestParam("id") Long id){
        Post post = postService.findOne(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(PostCreateDto.from(post));
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<PostDto>> getPostLists(){
//        List<PostDto> lists = postService.findPosts();
//        return ResponseEntity.status(HttpStatus.OK).body(lists);
//    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@RequestParam HashMap<String, String> param) {
        List<Post> posts = postService.findPosts(param);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts)
            postDtos.add(PostDto.from(post));
        return ResponseEntity.status(HttpStatus.OK).body(postDtos);
    }

    @PostMapping("/doWrite")
    public ResponseEntity<PostDto> write(@RequestBody PostCreateDto postCreateDto) {

        PostDto postDto = PostDto.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        Long id = postService.register(postDto);
        Post post = postService.findOne(id).orElse(null); // 저장 됐는지 체크
        return ResponseEntity.status(HttpStatus.OK).body(PostDto.from(post));
    }

    @PostMapping("/doModify")
    public void modify(@RequestBody PostUpdateDto postUpdateDto){
        Post post = PostUpdateDto.toEntity(postUpdateDto);
        postService.updateById(post.getTitle(), post.getContent(), post.getId());
    }
}
