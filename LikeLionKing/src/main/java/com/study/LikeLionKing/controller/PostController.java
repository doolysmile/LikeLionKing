package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.Request.PostCreateRequest;
import com.study.LikeLionKing.Request.PostModifyRequest;
import com.study.LikeLionKing.domain.dto.PostDto;
import com.study.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @GetMapping("/test/c")
//    String create(){
//        PostDto postDto = new PostDto();
//        postDto.setTitle("asd");
//        postDto.setContent("aqwe");
//        Long id = postService.save(postDto);
//        System.out.println(postService.findById(id));
//        return postService.findById(id).toString();
//    }
//    @GetMapping("/test/r")
//    String retrieve(){
//        PostDto temp = postService.findById(1L).get();
//        System.out.println(temp);
//        return temp.toString();
//    }
//    // TODO 오류발생
//    @GetMapping("/test/ra")
//    String retrieveAll(){
//        List<PostDto> temp = postService.findAll();
//        System.out.println(temp.toString());
//        return temp.toString();
//    }
//    @GetMapping("/test/u")
//    String update(){
//        PostDto postDto = postService.findById(1L).get();
//        postDto.setTitle("바꿈");
//        postDto.setContent("바꿈 내용");
//        postService.update(postDto);
//        System.out.println(postDto.toString());
//        return postDto.toString();
//    }
//    @GetMapping("/test/d")
//    String delete(){
//        System.out.println(postService.findAll());
//        postService.delete(1L);
//        System.out.println(postService.findAll());
//        return "delete";
//    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> create(@RequestBody PostCreateRequest createRequest){
        PostDto postDto = PostDto.builder()
                .title(createRequest.getTitle())
                .content(createRequest.getContent())
                .postRole(createRequest.getPostRole())
                .build();
        long id = postService.save(postDto);

        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<PostDto> retrieve(@RequestParam("id") Long id){
        System.out.println(id);
        PostDto postDto = postService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PostDto>> retrieveAll(){
        List<PostDto> postDtoList = postService.findAll();
        return  ResponseEntity.status(HttpStatus.OK).body(postDtoList);
    }

    @PostMapping("/update")
    public ResponseEntity<PostDto> update(@RequestBody PostModifyRequest modifyRequest){
        PostDto postDto = postService.findById(modifyRequest.getId());

        System.out.println(postDto);
        postDto.setContent(modifyRequest.getContent());
        postDto.setTitle(modifyRequest.getTitle());

        postService.update(postDto);

        System.out.println(postService.findById(modifyRequest.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @GetMapping("/delete")
    public ResponseEntity<PostDto> delete(@RequestParam("id") Long id){
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

}

