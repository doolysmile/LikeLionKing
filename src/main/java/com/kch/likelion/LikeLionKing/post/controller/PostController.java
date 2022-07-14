package com.kch.likelion.LikeLionKing.post.controller;



import com.kch.likelion.LikeLionKing.post.domain.Post;
import com.kch.likelion.LikeLionKing.post.domain.PostRequestDto;
import com.kch.likelion.LikeLionKing.post.domain.PostResponseDto;
import com.kch.likelion.LikeLionKing.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;

    @PostMapping("write")
    public ResponseEntity<PostResponseDto> writePost(@RequestBody PostRequestDto postRequest){

        System.out.println(postRequest.getContent());
        Post newPost = postService.insert(postRequest.newPost());
        System.out.println(newPost.getPostSeq());
        if(newPost != null){
            return ResponseEntity.status(HttpStatus.OK).body(new PostResponseDto(true, "가입완료"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new PostResponseDto(true, "가입실패"));
    }
    // TODO : 실패 했을 때 예외 처리 해줘야함.
    @PostMapping("update")
    public ResponseEntity<Object> updatePost(@RequestBody PostRequestDto postWriteRequest){
        postService.update(postWriteRequest.newPost());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    // TODO : 실패 했을 때 예외 처리 해줘야함.
    @GetMapping("delete/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable Long postId){
        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // TODO : 성공 메세지를 함꼐 출력 할 수 있도록 만들기.
    @GetMapping("list")
    public ResponseEntity<List<Object>> listPost(){
        List<Post> all = postService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(all.toArray()));
    }


}
