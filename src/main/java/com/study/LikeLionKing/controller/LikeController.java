package com.study.LikeLionKing.controller;

import com.study.LikeLionKing.request.LikeCreateRequest;
import com.study.LikeLionKing.response.ResponseData;
import com.study.LikeLionKing.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usr/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
}
