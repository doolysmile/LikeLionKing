package postsite.postsitespring.domain.like.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.like.domain.Like;
import postsite.postsitespring.domain.post.domain.Post;

@Repository
public class JdbcTemplateLikeRepository implements LikeRepository {
    @Override
    public long save(Like like) {
        return 0;
    }

    @Override
    public void delete() {

    }

}
