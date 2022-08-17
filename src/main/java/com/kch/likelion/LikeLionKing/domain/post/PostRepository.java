package com.kch.likelion.LikeLionKing.domain.post;


import com.kch.likelion.LikeLionKing.domain.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post insert(Post post);

    void update(Post post);

    void delete(long postId);

    List<Post> findAll(int boardType, int offset, int limit, String searchKeyword);

    Optional<Post> findById(Long id);

    void increaseViews(Post post);
}
