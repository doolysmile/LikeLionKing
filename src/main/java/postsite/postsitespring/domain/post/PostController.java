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
    @PostMapping()
    public ResponseEntity<PostCreate.ResponseDto> articleDoWrite(
            @RequestBody PostCreate.RequestDto body
    ) {
        Post post = body.toEntity();

        long id = postService.save(post);
        post.setId(id);

        ResponseEntity result =  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PostCreate.ResponseDto(post));
        return result;
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
    ) {
        Post post = postService.findById(articleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PostRead.ResponseDto(post));
    }

    // Update
    @PutMapping("/{articleId}")
    public ResponseEntity<String> articleModify(
            @PathVariable Long articleId,
            @RequestBody PostUpdate.RequestDto body
            ) {
        Post post = postService.findById(articleId);

        if(post == null){
            //에러
        }

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
    ) {
        postService.delete(articleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

}
