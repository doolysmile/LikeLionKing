package com.study.LikeLionKing.controller;

import com.study.LikeLionKing.domain.dto.LikeDto;
import com.study.LikeLionKing.request.LikeCreateRequest;
import com.study.LikeLionKing.request.LikeDeleteRequest;
import com.study.LikeLionKing.response.ResponseData;
import com.study.LikeLionKing.service.LikeService;
import com.study.LikeLionKing.service.PostService;
import com.study.LikeLionKing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usr/like")
public class LikeController {

    private final LikeService likeService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public LikeController(LikeService likeService, PostService postService, UserService userService) {
        this.likeService = likeService;
        this.postService = postService;
        this.userService = userService;
    }




    @PostMapping("/add")
    public ResponseData save(@RequestBody LikeCreateRequest likeCreateRequest){
        if(postService.findById(likeCreateRequest.getPostId())==null){
            ResponseData.failResponse("해당 post는 존재하지 않습니다.");
        }

        if(userService.findById(likeCreateRequest.getUserId())==null){
            ResponseData.failResponse("해당 user는 존재하지 않습니다.");
        }

        if(likeService.findByUserIdAndPostId(likeCreateRequest.getUserId(), likeCreateRequest.getPostId())!=null){
            return ResponseData.failResponse("이미 좋아요를 누른 게시글입니다.");
        }

        LikeDto likeDto = LikeDto.builder()
                .postId(likeCreateRequest.getPostId())
                .userId(likeCreateRequest.getUserId())
                .build();

        long id = likeService.save(likeDto);

        return ResponseData.successResponse(likeService.findById(id));
    }

    @PostMapping("delete")
    public ResponseData delete(@RequestBody LikeDeleteRequest likeDeleteRequest){
        if(postService.findById(likeDeleteRequest.getPostId())==null){
            ResponseData.failResponse("해당 post는 존재하지 않습니다.");
        }

        if(userService.findById(likeDeleteRequest.getUserId())==null){
            ResponseData.failResponse("해당 user는 존재하지 않습니다.");
        }

        if(likeService.findByUserIdAndPostId(likeDeleteRequest.getUserId(), likeDeleteRequest.getPostId())==null){
            return ResponseData.failResponse("삭제할 좋아요 데이터가 없습니다.");
        }

        LikeDto found = likeService.findByUserIdAndPostId(likeDeleteRequest.getUserId(), likeDeleteRequest.getPostId());

        likeService.delete(found.getId());

        return ResponseData.successResponse("좋아요 삭제 완료");
    }

}
