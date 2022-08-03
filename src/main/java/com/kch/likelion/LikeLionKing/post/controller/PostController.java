package com.kch.likelion.LikeLionKing.post.controller;



import com.kch.likelion.LikeLionKing.post.domain.Post;
import com.kch.likelion.LikeLionKing.post.domain.PostDto;
import com.kch.likelion.LikeLionKing.post.domain.PostRequestDto;
import com.kch.likelion.LikeLionKing.post.domain.PostResponseDto;
import com.kch.likelion.LikeLionKing.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

// TODO : PostResposneDTO 합치는 쪽으로 생각해보기
@RestController
@AllArgsConstructor
@RequestMapping("/usr/article")
public class PostController {

    private final PostService postService;

//    @GetMapping("/write")
//    public ResponseEntity<List<PostDto>> writePostForm(){
//
//    }


    @PostMapping("/doWrite")
    public ResponseEntity<PostDto> doWritePost(@RequestBody PostRequestDto postRequest){

        System.out.println(postRequest.getContent());
        Post newPost = postService.insert(postRequest.newPost());
        System.out.println(newPost.getPostSeq());
        if(newPost != null){
            return ResponseEntity.status(HttpStatus.OK).body(new PostDto(newPost));
        }
        // TODO 리턴 값 생각 해보기
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/modify")
    public ResponseEntity<PostDto> modifyPostForm(@RequestParam("id") Long id){

        return ResponseEntity.status(HttpStatus.OK).body(new PostDto(postService.findById(id)));
    }
    @PostMapping("/doModify")
    public ResponseEntity<PostDto> doModifyPost(@RequestBody PostRequestDto postWriteRequest){

        return ResponseEntity.status(HttpStatus.OK).body(new PostDto(postService.update(postWriteRequest.newPost())));
    }

    @GetMapping("/detail")
    public ResponseEntity<PostDto> detailPost(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new PostDto(postService.findById(id)));
    }

    // TODO : 실패 했을 때 예외 처리 해줘야함.
    @GetMapping("delete/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable Long postId){
        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).body(new PostResponseDto(true, "삭제완료"));
    }

    // TODO : 성공 여부와 객체 리턴을 함꼐 출력 할 수 있도록 만들기.
//    @GetMapping("list")
//    public ResponseEntity<List<Object>> listPost(){
//        List<PostDto> postDtoLists= postService.findAll().stream()
//                .map(PostDto::new).collect(toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(postDtoLists.toArray()));
//    }

    @GetMapping("list")
    public ResponseEntity<List<PostDto>> listPost(@RequestParam(value="offset", defaultValue = "0") int offset,
                                                  @RequestParam(value = "limit", defaultValue = "5") int limit,
                                                  @RequestParam(value = "searchKeyword", defaultValue = "")String searchKeyword){

        List<PostDto> postDtoLists= postService.findAll(offset, limit, searchKeyword).stream()
                .map(PostDto::new).collect(toList());

        return ResponseEntity.status(HttpStatus.OK).body(postDtoLists);
    }

}
