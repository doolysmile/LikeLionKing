package postsite.postsitespring.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.common.exception.ResourceNotFoundException;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.PostCreate;
import postsite.postsitespring.domain.post.dto.PostRead;
import postsite.postsitespring.domain.post.dto.PostUpdate;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/usr/article")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create
    @PostMapping()
    public ResponseEntity<PostCreate.ResponseDto> articleDoWrite(
            @RequestBody PostCreate.RequestDto body
    ) {
        Post post = body.toEntity();

        long id = postService.save(post);
        post.setId(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PostCreate.ResponseDto(post));
    }

    // Read
    @GetMapping()
    public ResponseEntity<List<PostRead.ResponseDto>> articleList(
            @RequestParam() Long boardId,
            @RequestParam(defaultValue = "0") Long page,
            @RequestParam(required = false) String searchKeyword
    ) {
        List<Post> posts =  Objects.isNull(searchKeyword) ?
                postService.findAll(boardId, page) :
                postService.findAll(boardId, page, searchKeyword);

        // entity => dto
        List<PostRead.ResponseDto> responseBody = posts.stream()
                .map(PostRead.ResponseDto::new)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<PostRead.ResponseDto> articleDetail(
            @PathVariable() Long articleId
    ) throws ResourceNotFoundException {
        // TODO exception 공통 처리. RestControllerAdvice 사용.
        // TODO exception message 중복 제거해보기.
        // TODO message 응답 안되는부분 고쳐보기
        // TODO findById 너무 중복됨. 방법 없을까?
        Post post = postService
                .findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :" + articleId));

        // TODO 매번 ResponseEntity 적용 너무 중복됨. 방법 찾아보자.
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PostRead.ResponseDto(post));
    }

    // Update
    @PutMapping("/{articleId}")
    public ResponseEntity<String> articleModify(
            @PathVariable Long articleId,
            @RequestBody PostUpdate.RequestDto body
            ) throws ResourceNotFoundException {

        Post post = postService
                .findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :" + articleId));

        body.updateEntity(post);

        postService.update(post);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

    //Delete
    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable Long articleId
    ) throws ResourceNotFoundException {
        postService
        .findById(articleId)
        .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :" + articleId));

        postService.delete(articleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

}
