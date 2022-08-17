package com.hsy.likelion.LikeLionKing.service;

import com.hsy.likelion.LikeLionKing.domain.Likes;
import com.hsy.likelion.LikeLionKing.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikesService {
    private final LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public Optional<Likes> findByMemberIdAndPostId(Likes likes) {
        return likesRepository.findByMemberIdAndPostId(likes);
    }

    @Transactional
    public Long save(Likes likes) {
        Long likesId = likesRepository.save(likes);
        increaseLikes(likes.getPostId());

        return likesId;
    }

    @Transactional
    public void delete(Long likeId, Long postId) {
        likesRepository.delete(likeId);
        decreaseLikes(postId);
    }

    // 게시물 좋아요 수 증가
    public void increaseLikes(Long postId) {
        likesRepository.increaseLikes(postId);
    }

    // 게시물 좋아요 수 감소
    public void decreaseLikes(Long postId) {
        likesRepository.decreaseLikes(postId);
    }
}
