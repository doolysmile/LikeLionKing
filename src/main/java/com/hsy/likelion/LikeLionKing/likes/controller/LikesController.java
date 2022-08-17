package com.hsy.likelion.LikeLionKing.likes.controller;

import com.hsy.likelion.LikeLionKing.likes.domain.Likes;
import com.hsy.likelion.LikeLionKing.likes.dto.LikesDto;
import com.hsy.likelion.LikeLionKing.likes.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikesController {
    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    // 좋아요 저장
    @PostMapping("/likes/save")
    public ResponseEntity<Long> save(@RequestBody LikesDto likesDto) {
        Likes likes = LikesDto.toEntity(likesDto);
        Likes findLikes = likesService.findByMemberIdAndPostId(likes).orElse(null);

        // 이미 좋아요 기록이 있다면 409
        if (findLikes != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(findLikes.getId());
        }
        // 좋아요 저장
        Long likesId = likesService.save(likes);
        return ResponseEntity.status(HttpStatus.OK).body(likesId);
    }

    // 좋아요 삭제
    @PostMapping("/likes/delete")
    public ResponseEntity<String> delete(@RequestBody LikesDto likesDto) {
        Likes likes = LikesDto.toEntity(likesDto);
        Likes findLikes = likesService.findByMemberIdAndPostId(likes).orElse(null);

        // 좋아요 누른 기록이 없다면 404
        if (findLikes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("fail");
        }
        // 좋아요 삭제
        likesService.delete(findLikes.getId(), findLikes.getPostId());
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
