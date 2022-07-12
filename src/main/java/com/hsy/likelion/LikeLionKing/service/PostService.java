package com.hsy.likelion.LikeLionKing.service;

import com.hsy.likelion.LikeLionKing.domain.Post;
import com.hsy.likelion.LikeLionKing.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    // 게시글 등록
    public Post save(Post post) {
        return postRepository.save(post);
    }

    // 게시글 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 id로 조회
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    // 게시글 수정
    public void update(Post post) {
        postRepository.update(post);
    }

    // 게시글 삭제
    public void delete(Long id) {
        postRepository.delete(id);
    }
}
