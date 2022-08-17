package postsite.postsitespring.domain.post.repository;

import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.like.domain.Like;
import postsite.postsitespring.domain.like.repository.LikeRepository;
import postsite.postsitespring.domain.post.domain.PostLike;

@Repository
public class JdbcTemplatePostLikeRepository implements PostLikeRepository {
    @Override
    public long save(PostLike postLike) {
        return 0;
    }

    @Override
    public long countByPost(Long postId) {
        return 0;
    }

    @Override
    public void delete() {

    }

}
