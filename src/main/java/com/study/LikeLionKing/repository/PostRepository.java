package com.study.LikeLionKing.repository;

import com.study.LikeLionKing.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Long save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    void update(Post post);
    void delete(Long id);
}
