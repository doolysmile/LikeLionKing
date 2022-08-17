package postsite.postsitespring.domain.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.like.domain.Like;

@RestController
@RequestMapping("/usr/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // 좋아요 생성
    @PostMapping()
    public void createLike(@RequestBody Like body){
        likeService.createLike(body);
    }

    // 좋아요 삭제
    @DeleteMapping()
    public void deleteLike(){

    }
}
