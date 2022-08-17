package postsite.postsitespring.domain.like.repository;

import postsite.postsitespring.domain.like.domain.Like;

public interface LikeRepository {
    long save(Like like);
    void delete();

}
