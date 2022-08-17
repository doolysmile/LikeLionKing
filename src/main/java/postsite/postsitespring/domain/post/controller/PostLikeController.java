package postsite.postsitespring.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.common.exception.ResourceNotFoundException;
import postsite.postsitespring.domain.post.domain.PostLike;
import postsite.postsitespring.domain.post.service.PostLikeService;
import postsite.postsitespring.domain.post.service.PostService;
import postsite.postsitespring.domain.user.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/usr/article/{articleId}/likes/user/{userId}")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;
    private final PostService postService;
    private final UserService userService;

    // 좋아요 생성
    @PostMapping()
    public void createPostLike(
            @PathVariable long articleId,
            @PathVariable long userId //TODO 로그인 세션으로 처리
    ) throws ResourceNotFoundException {
        // post 체크
        postService
                .findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :" + articleId));
        // user 체크
        userService
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + articleId));

        // make entity
        PostLike postLike = PostLike.builder()
                .postId(articleId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();

        postLikeService.createPostLike(postLike);
    }

    // 좋아요 삭제
    @DeleteMapping()
    public void deleteLike(
            @PathVariable long userId, //TODO 로그인 세션으로 처리
            @PathVariable long articleId
    ) throws ResourceNotFoundException {
        // post 체크
        postService
                .findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :" + articleId));
        // user 체크
        userService
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + articleId));

        postLikeService.removePostLike(articleId, userId);
    }
}
