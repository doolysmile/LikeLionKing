package postsite.postsitespring.domain.post.repository;

import postsite.postsitespring.domain.post.domain.PostLike;

public interface PostLikeRepository {
    long save(PostLike postLike);
    long countByPost(Long postId);
    void delete();

}
