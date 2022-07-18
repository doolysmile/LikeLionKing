package postsite.postsitespring.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.PostCreate;
import postsite.postsitespring.domain.post.dto.PostSave;
import postsite.postsitespring.domain.post.dto.PostUpdate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
    public PostCreate.ResponseDto articleDoWrite(
            @RequestBody PostCreate.RequestDto body
            ) {
        Post post = body.toEntity();
        long id = postService.save(post);
        post.setId(id);

        return new PostCreate.ResponseDto(post);
    }

    // Read
    @GetMapping("")
    public List<PostSave.ResponseDto> articleList(
            @RequestParam() Long boardId,
            @RequestParam(defaultValue = "0") Long page,
            @RequestParam(required = false) String searchKeyword
    ) {
        List<Post> posts =  Objects.isNull(searchKeyword) ?
                postService.findAll(boardId, page) :
                postService.findAll(boardId, page, searchKeyword);

        return posts.stream()
                .map(post -> new PostSave.ResponseDto(post))
                .collect(Collectors.toList());
    }

    @GetMapping("/{articleId}")
    public PostSave.ResponseDto articleDetail(
            @PathVariable() Long articleId
    ) {
        Post post = postService.findById(articleId);
        return new PostSave.ResponseDto(post);
    }

    // Update
    @PutMapping("/{atricleId}")
    public String articleModify(
            @PathVariable Long atricleId,
            @RequestBody PostUpdate.RequestDto body
            ) {
        Post post = body.toEntity();
        post.setId(atricleId);

        postService.update(post);

        return "success";
    }

    //Delete
    @DeleteMapping("/{atricleId}")
    public String deleteArticle(
            @PathVariable Long atricleId
    ) {
        //postService
        postService.delete(atricleId);
        return "success";
    }

}
