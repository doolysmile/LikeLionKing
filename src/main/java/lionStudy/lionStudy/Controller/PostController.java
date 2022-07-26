package lionStudy.lionStudy.Controller;

import lionStudy.lionStudy.domain.DTO.PostDto;
import lionStudy.lionStudy.domain.Post;
import lionStudy.lionStudy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usr/article")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/detail")
    public ResponseEntity<PostDto> getDetail(@RequestParam("id") Long id){
        Post post = postService.findOne(id).orElse(null); // Optional -> 없으면 null값 반환
        return ResponseEntity.status(HttpStatus.OK).body(PostDto.from(post));
    }
}
