package postsite.postsitespring.domain.post.repository;

import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.post.domain.PostLike;

@Repository
public class JdbcTemplatePostLikeRepository implements PostLikeRepository {
    @Override
    public void save(PostLike postLike) {

    }

    @Override
    public long countByPost(Long postId) {
        return 0;
    }

    @Override

    public void delete(long articleId, long userId) {

    }

}
