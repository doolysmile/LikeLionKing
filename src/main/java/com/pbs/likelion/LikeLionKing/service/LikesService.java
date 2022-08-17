package com.pbs.likelion.LikeLionKing.service;

import com.pbs.likelion.LikeLionKing.domain.Likes;
import com.pbs.likelion.LikeLionKing.domain.dto.LikesDto;
import com.pbs.likelion.LikeLionKing.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private LikesRepository likesRepository;

    public LikesDto findByMemberIdAndPostId(LikesDto likesDto) {
        Likes likes = likesDto.toEntity(likesDto);
        Likes findLikes = likesRepository.findByMemberIdAndPostId(likes).orElse(null);

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
}
