package postsite.postsitespring.domain.post.repository;

import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.PostCreate;

import java.util.List;

public interface PostRepository {
    long save(Post post);
    Post findById(Long id);
    List<Post> findAll(Long boardId,Long page);
    List<Post> findAll(Long boardId,Long page, String searchKeyword);
    void update(Post post);
    void delete(Long id);
}

