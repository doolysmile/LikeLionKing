package com.kch.likelion.LikeLionKing.post;


import com.kch.likelion.LikeLionKing.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post insert(Post post);

    void update(Post post);

    void delete(long postId);

    List<Post> findAll(int offset, int limit, String searchKeyword);

    Optional<Post> findById(Long id);
}
