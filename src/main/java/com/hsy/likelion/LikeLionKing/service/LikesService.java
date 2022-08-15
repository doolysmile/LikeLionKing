package com.hsy.likelion.LikeLionKing.service;

import com.hsy.likelion.LikeLionKing.domain.Likes;
import com.hsy.likelion.LikeLionKing.repository.LikesRepository;
import org.springframework.stereotype.Service;

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

    public Long save(Likes likes) {
        return likesRepository.save(likes);
    }

    public void delete(Long id) {
        likesRepository.delete(id);
    }
}
