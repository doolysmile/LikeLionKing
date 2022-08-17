package com.pbs.likelion.LikeLionKing.repository;

import com.pbs.likelion.LikeLionKing.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();

    List<Post> find_LatestPosts10(int categoryId);
    List<Post> findByCategory_WithPageNum(int categoryId, int pageNum);
    List<Post> findByCategory_WithKeyword(int categoryId, String keyword);

    void deleteById(Long id);

    void updateById(String title, String content, Long id);
    void updateTitleById(String title, Long id);

    void updateContentById(String content, Long id);


    void increaseView(Long id);

    void increaseLikes(Long id);
    void decreaseLikes(Long id);
}
