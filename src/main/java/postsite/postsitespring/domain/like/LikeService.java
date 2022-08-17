package postsite.postsitespring.domain.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postsite.postsitespring.domain.like.domain.Like;
import postsite.postsitespring.domain.like.repository.LikeRepository;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public void createLike(Like like) {
        likeRepository.save(like);
    }
}
