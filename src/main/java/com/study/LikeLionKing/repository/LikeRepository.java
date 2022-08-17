package com.study.LikeLionKing.repository;

import com.study.LikeLionKing.domain.Like;

import java.util.Optional;

public interface LikeRepository {
    Long save(Like like);
    Optional<Like> findById(Long id);
    Optional<Like> findByUserIdAndPostId(Long userId,Long postId);
    void delete(Long id);
    void increaseLike(Long postId);
    void decreaseLike(Long postId);
}
