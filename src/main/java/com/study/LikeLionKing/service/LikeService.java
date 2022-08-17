package com.study.LikeLionKing.service;

import com.study.LikeLionKing.domain.Like;
import com.study.LikeLionKing.domain.dto.LikeDto;
import com.study.LikeLionKing.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {


    private final LikeRepository likeRepository;
    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public long save(LikeDto likeDto) {
        Long id = likeRepository.save(likeDto.toEntity());
        return id;
    }

    public LikeDto findById(long id) {
        Optional<Like> like = likeRepository.findById(id);

        if(like.isEmpty()){
            return null;
        }
        Like l = like.get();
        LikeDto likeDto = LikeDto.builder()
                .postId(l.getPostId())
                .userId(l.getUserId())
                .id(l.getId())
                .build();

        return likeDto;
    }

    public LikeDto findByUserIdAndPostId(long userId, long postId){
        Optional<Like> like = likeRepository.findByUserIdAndPostId(userId,postId);

        if(like.isEmpty()){
            return null;
        }

        Like l = like.get();
        LikeDto likeDto = LikeDto.builder()
                .postId(l.getPostId())
                .userId(l.getUserId())
                .id(l.getId())
                .build();

        return likeDto;
    }
}
