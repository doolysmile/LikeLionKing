package com.hsy.likelion.LikeLionKing.repository;

import com.hsy.likelion.LikeLionKing.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    void update(Post post);
    void delete(Long id);
}
