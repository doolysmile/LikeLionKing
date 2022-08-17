package postsite.postsitespring.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postsite.postsitespring.domain.post.domain.PostLike;
import postsite.postsitespring.domain.post.repository.PostLikeRepository;


@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    public long countByPost(Long postId) {
        return postLikeRepository.countByPost(postId);
    }

    public void createPostLike(PostLike postLike) {
        postLikeRepository.save(postLike);
    }

    public void removePostLike(long articleId, long userId) {
        postLikeRepository.delete(articleId, userId);
    }
}
