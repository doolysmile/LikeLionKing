package com.kch.likelion.LikeLionKing.domain.like.service;

import com.kch.likelion.LikeLionKing.domain.like.domain.Like;
import com.kch.likelion.LikeLionKing.domain.like.repository.JdbcLikeRepository;
import com.kch.likelion.LikeLionKing.domain.post.JdbcPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {
    private final JdbcLikeRepository jdbcLikeRepository;
    private final JdbcPostRepository jdbcPostRepository;

    public Optional<Like> findByUserIdAndPostId(Like findLike){
        return jdbcLikeRepository.findByUserIdAndPostId(findLike.getUserId(), findLike.getPostId());
    }
    public Like save(Like saveLike) {
        // 좋아요 한 적이 없는 경우
        if(findByUserIdAndPostId(saveLike).isEmpty()){
            jdbcPostRepository.increaseLikes(saveLike.getPostId());
            return jdbcLikeRepository.save(saveLike);
        }
        return null;
    }

    public void delete(Like deleteLike) {
        // 좋아요 한 적이 있는 경우
        if(findByUserIdAndPostId(deleteLike).isPresent()){
            jdbcPostRepository.decreaseLikes(deleteLike.getPostId());
            jdbcLikeRepository.delete(deleteLike.getUserId(), deleteLike.getPostId());
        }
    }


}
