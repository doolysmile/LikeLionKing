package com.study.LikeLionKing.controller;


import com.study.LikeLionKing.request.PostCreateRequest;
import com.study.LikeLionKing.request.PostModifyRequest;
import com.study.LikeLionKing.domain.dto.PostDto;
import com.study.LikeLionKing.response.ResponseData;
import com.study.LikeLionKing.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/usr/article")

// TODO: 2022-08-04  
/**
 * https://velog.io/@banjjoknim/RestControllerAdvice 여기있는 RestControllerAdvice사용해보기
 */
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    // 엔드포인트 구현

    @GetMapping("/detail")
    public ResponseData detail(@RequestParam("id")long id){
        PostDto postDto = postService.findById(id);
        ResponseData<PostDto> responseData;
        if(postDto == null){
            responseData = ResponseData.failResponse("해당 id를 가진 post가 존재하지 않습니다.");
        }
        else{
            responseData = ResponseData.successResponse(postDto);
        }
        return responseData;
    }

    @GetMapping("/list")
    public ResponseData list(@RequestParam HashMap<String,String> paramMap){
        if(paramMap.isEmpty()){
            return ResponseData.failResponse("게시판 역할을 선택해주세요.");
        }
        List<PostDto> postDtos = postService.findAll(paramMap);

        ResponseData<List<PostDto>> responseData;

        if(postDtos.isEmpty()){
            responseData = ResponseData.failResponse("해당 페이지에 존재하는 post가 없습니다.");
        }
        else{
            responseData = ResponseData.successResponse(postDtos);
        }
        return responseData;
    }

    @PostMapping("/doWrite")
    public ResponseData write(@RequestBody PostCreateRequest createRequest){
        if(createRequest.getPostRole()==-1){
            return ResponseData.failResponse("게시판 역할을 선택하지 않았습니다.");
        }
        if(createRequest.getTitle()==null ){
            return ResponseData.failResponse("제목이 존재하지 않습니다");
        }
        if(createRequest.getContent()==null){
            return ResponseData.failResponse("내용이 존재하지 않습니다");
        }

        PostDto postDto = PostDto.builder()
                .title(createRequest.getTitle())
                .content(createRequest.getContent())
                .postRole(createRequest.getPostRole())
                .build();

        long id = postService.save(postDto);

        ResponseData responseData = ResponseData.successResponse(postService.findById(id));
        return responseData;
    }

    @PostMapping("/doModify")
    public ResponseData modify(@RequestBody PostModifyRequest modifyRequest){
        if(modifyRequest.getId()==-1){
            return ResponseData.failResponse("수정할 게시글 ID를 입력해주세요.");
        }
        if(modifyRequest.getTitle()==null ){
            return ResponseData.failResponse("제목이 존재하지 않습니다");
        }
        if(modifyRequest.getContent()==null){
            return ResponseData.failResponse("내용이 존재하지 않습니다");
        }

        PostDto postDto = postService.findById(modifyRequest.getId());

        postDto.setContent(modifyRequest.getContent());
        postDto.setTitle(modifyRequest.getTitle());

        postService.update(postDto);

        return ResponseData.successResponse(postDto);
    }

    @GetMapping("/recommended/{id}")
    public void recommended(@PathVariable long id){
        postService.recommendedInc(id);
    }

}

