package com.pbs.likelion.LikeLionKing.controller;


import com.pbs.likelion.LikeLionKing.domain.Likes;
import com.pbs.likelion.LikeLionKing.domain.dto.LikesDto;
import com.pbs.likelion.LikeLionKing.service.LikesService;
import com.pbs.likelion.LikeLionKing.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;
    private final PostService postService;

    /**
     * 고민했던 점
     * 게시글에서 "좋아요"를 클릭 시,
     * 1. 먼저 테이블에 있는지 확인을 거침
     * 2. 테이블에 있는 경우 -> 좋아요를 누른 상태 -> (Likes 테이블)에서 해당 데이터 삭제 -> (Post 테이블)좋아요를 감소 시켜야됨
     * 3. 테이블에 없는 경우 -> 좋아요를 누르지 않은 상태 -> (Post 테이블) 좋아요를 증가 시켜야됨.
     */

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody LikesDto likesDto) {

        LikesDto findLikes = likesService.findByMemberIdAndPostId(likesDto);
        // 이미 좋아요를 누른경우
       if(findLikes != null){
           /**
            *  고민했던점 -> 좋아요를 다시 누르면 그저 취소가 아닐련지
            */
           likesService.delete(findLikes);
           likesService.decrease(findLikes);
           return ResponseEntity.status(HttpStatus.OK).body(findLikes.getId());
        }


       Long id = likesService.save(likesDto);
       likesService.increase(likesDto);

       return ResponseEntity.status(HttpStatus.OK).body(id);
    }


}
