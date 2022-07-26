package postsite.postsitespring.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/doWrite")
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
    @GetMapping("/list")
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

    @GetMapping("/detail")
    public ResponseEntity<PostRead.ResponseDto> articleDetail(
            @RequestParam() Long id
    ) {
        Post post = postService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PostRead.ResponseDto(post));
    }

    // Update
    @PostMapping("/doModify")
    public ResponseEntity<String> articleModify(
            @RequestBody PostUpdate.RequestDto body
            ) {
        Post post = body.toEntity();

        postService.update(post);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

    //Delete
    @PostMapping("/doDelete/{articleId}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable Long articleId
    ) {
        postService.delete(articleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

}
