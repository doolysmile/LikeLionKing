package com.hsy.likelion.LikeLionKing.likes.repository;

import com.hsy.likelion.LikeLionKing.likes.domain.Likes;

import java.util.Optional;

public interface LikesRepository {
    Long save(Likes likes);

    Optional<Likes> findByMemberIdAndPostId(Likes likes);

    void delete(Long id);
    void increaseLikes(Long postId);

    void decreaseLikes(Long postId);
}
