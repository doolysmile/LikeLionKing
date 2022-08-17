package postsite.postsitespring.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.post.dto.CreatePostLike;
import postsite.postsitespring.domain.post.service.PostLikeService;

@RestController
@RequestMapping("/usr/article/{articleId}")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    // 좋아요 생성
    @PostMapping()
    public void createLike(){

        //postLikeService.createLike(like);


    }
    // 좋아요 개수
    @GetMapping()
    public long countByPost(@RequestParam Long postId){
        return postLikeService.countByPost(postId);
    }
    // 좋아요 삭제
    @DeleteMapping()
    public void deleteLike(){

    }
}
