package postsite.postsitespring.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postsite.postsitespring.domain.like.domain.Like;
import postsite.postsitespring.domain.like.repository.LikeRepository;
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
}
