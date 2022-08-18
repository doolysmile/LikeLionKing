package com.kch.likelion.LikeLionKing.domain.like.controller;

import com.kch.likelion.LikeLionKing.domain.like.domain.Like;
import com.kch.likelion.LikeLionKing.domain.like.dto.LikeDto;
import com.kch.likelion.LikeLionKing.domain.like.service.LikeService;
import com.kch.likelion.LikeLionKing.global.common.ApiResult;
import com.kch.likelion.LikeLionKing.global.common.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/usr")
public class LikeController {

    private final LikeService likeService;
    // TODO : pathvariable
    @PostMapping("/like")
    public ApiResult<Like> likePost(@RequestBody LikeDto likeDto){
        Like findLike = likeService.save(likeDto.toEntity(likeDto));
        if(findLike != null){
            return ApiUtils.success(findLike);
        }
        return (ApiResult<Like>) ApiUtils.error("좋아요 한 적이 있습니다.", 400);
    }

    @PostMapping("/unlike")
    public ApiResult<Like> unlikePost(@RequestBody LikeDto likeDto){
        Optional<Like> deleteLike = likeService.delete(likeDto.toEntity(likeDto));
        if(deleteLike.isPresent()){
            return ApiUtils.success(deleteLike.get());
        }
        return (ApiResult<Like>) ApiUtils.error("해당 좋아요가 없습니다.", 400);
    }
}
