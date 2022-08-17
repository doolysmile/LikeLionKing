package com.pbs.likelion.LikeLionKing.service;

import com.pbs.likelion.LikeLionKing.domain.Likes;
import com.pbs.likelion.LikeLionKing.domain.dto.LikesDto;
import com.pbs.likelion.LikeLionKing.repository.LikesRepository;
import com.pbs.likelion.LikeLionKing.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;

    public LikesService(LikesRepository likesRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
    }

    public LikesDto findByMemberIdAndPostId(LikesDto likesDto) {
        Likes likes = likesDto.toEntity(likesDto);
        Likes findLikes = likesRepository.findByMemberIdAndPostId(likes).orElse(null);

        // 좋아요를 안누른 경우
        if (findLikes == null){
            System.out.println("좋아요 안누른 상태");
            return null;
        }
        System.out.println("좋아요를 누른 상태");

        //좋아요를 누른 경우
        LikesDto build = LikesDto.builder()
                .id(findLikes.getId())
                .memberId(findLikes.getMemberId())
                .postId(findLikes.getPostId())
                .build();

        return build;

    }

    public Long save(LikesDto likesDto) {
        Likes likes = likesDto.toEntity(likesDto);
        return likesRepository.save(likes);
    }

    public void delete(LikesDto likesDto){
        likesRepository.delete(likesDto.getId());
    }

    public void decrease(LikesDto likesDto){
        postRepository.decreaseLikes(likesDto.getPostId());
    }

    public void increase(LikesDto likesDto){
        postRepository.increaseLikes(likesDto.getPostId());
    }
}
