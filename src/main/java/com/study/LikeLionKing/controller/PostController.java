package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.Request.PostCreateRequest;
import com.study.LikeLionKing.Request.PostModifyRequest;
import com.study.LikeLionKing.domain.dto.PostDto;
import com.study.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // CRUD 테스트
//    @PostMapping("/create")
//    public ResponseEntity<PostDto> create(@RequestBody PostCreateRequest createRequest){
//        PostDto postDto = PostDto.builder()
//                .title(createRequest.getTitle())
//                .content(createRequest.getContent())
//                .postRole(createRequest.getPostRole())
//                .build();
//        long id = postService.save(postDto);
//
//        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
//    }
//
//    @GetMapping("/find")
//    public ResponseEntity<PostDto> retrieve(@RequestParam("id") Long id){
//        System.out.println(id);
//        PostDto postDto = postService.findById(id);
//        return  ResponseEntity.status(HttpStatus.OK).body(postDto);
//    }
//
//    @GetMapping("/findAll")
//    public ResponseEntity<List<PostDto>> retrieveAll(){
//        List<PostDto> postDtoList = postService.findAll();
//        return  ResponseEntity.status(HttpStatus.OK).body(postDtoList);
//    }
//
//    @PostMapping("/update")
//    public ResponseEntity<PostDto> update(@RequestBody PostModifyRequest modifyRequest){
//        PostDto postDto = postService.findById(modifyRequest.getId());
//
//        System.out.println(postDto);
//        postDto.setContent(modifyRequest.getContent());
//        postDto.setTitle(modifyRequest.getTitle());
//
//        postService.update(postDto);
//
//        System.out.println(postService.findById(modifyRequest.getId()));
//        return ResponseEntity.status(HttpStatus.OK).body(postDto);
//    }
//
//    @GetMapping("/delete")
//    public ResponseEntity<PostDto> delete(@RequestParam("id") Long id){
//        postService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
//    }


    // 엔드포인트 구현

    @GetMapping("/detail")
    public ResponseEntity<PostDto> detail(@RequestParam("id")long id){
        System.out.println(id);
        PostDto postDto = postService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDto>> list(@RequestParam HashMap<String,String> paramMap){
        List<PostDto> postDtos = postService.findAll(paramMap);
        return ResponseEntity.status(HttpStatus.OK).body(postDtos);
    }

    @PostMapping("/doWrite")
    public ResponseEntity<PostDto> write(@RequestBody PostCreateRequest createRequest){
        PostDto postDto = PostDto.builder()
                .title(createRequest.getTitle())
                .content(createRequest.getContent())
                .postRole(createRequest.getPostRole())
                .build();
        long id = postService.save(postDto);

        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @PostMapping("/doModify")
    public ResponseEntity<PostDto> modify(@RequestBody PostModifyRequest modifyRequest){
        PostDto postDto = postService.findById(modifyRequest.getId());

        postDto.setContent(modifyRequest.getContent());
        postDto.setTitle(modifyRequest.getTitle());

        postService.update(postDto);

        System.out.println(postService.findById(modifyRequest.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

}

