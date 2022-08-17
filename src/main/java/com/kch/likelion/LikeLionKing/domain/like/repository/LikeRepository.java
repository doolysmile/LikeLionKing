package com.kch.likelion.LikeLionKing.domain.like.repository;

import com.kch.likelion.LikeLionKing.domain.like.domain.Like;

import java.util.Optional;

public interface LikeRepository {

    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    Like save(Like like);

    void delete(Long userId, Long postId);
}
