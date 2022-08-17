package com.kch.likelion.LikeLionKing.domain.like.controller;

import com.kch.likelion.LikeLionKing.domain.like.domain.Like;
import com.kch.likelion.LikeLionKing.domain.like.dto.LikeDto;
import com.kch.likelion.LikeLionKing.domain.like.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/usr")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<String> likePost(@RequestBody LikeDto likeDto){
        Like findLike = likeService.save(likeDto.toEntity(likeDto));
        return null;
    }

    @PostMapping("/unlike")
    public ResponseEntity<String> unlikePost(@RequestBody LikeDto likeDto){
        likeService.delete(likeDto.toEntity(likeDto));
        return null;
    }
}
