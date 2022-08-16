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
    void viewsInc(Long id,long views);
    List<Post> findByTitleAll(int boardId, String title);
    List<Post> findAll(int boardId);
    List<Post> findAll(int boardId, int page);
    void recommendedInc(long recommended,long id);
}
