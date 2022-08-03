package postsite.postsitespring.domain.post.repository;

import postsite.postsitespring.domain.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    long save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll(Long boardId,Long page);
    List<Post> findAll(Long boardId,Long page, String searchKeyword);
    void update(Post post);
    void delete(Long id);
}

