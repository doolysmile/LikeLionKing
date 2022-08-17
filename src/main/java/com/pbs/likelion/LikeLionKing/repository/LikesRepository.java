package com.pbs.likelion.LikeLionKing.repository;

import com.pbs.likelion.LikeLionKing.domain.Likes;

import java.util.Optional;

public interface LikesRepository {
    Long save(Likes likes);

    Optional<Likes> findById(Long id);
    Optional<Likes> findByMemberIdAndPostId(Likes likes);

    void delete(Long id);


}
