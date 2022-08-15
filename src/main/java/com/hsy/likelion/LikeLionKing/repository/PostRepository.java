package com.hsy.likelion.LikeLionKing.repository;

import com.hsy.likelion.LikeLionKing.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Long save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    List<Post> findByCategoryPage(Integer categoryId, Integer page, Integer size);
    List<Post> findByCategorySearchAll(Integer categoryId, String search, Integer size);
    void update(Post post);
    void delete(Long id);
    void increaseViews(Long id);
}
