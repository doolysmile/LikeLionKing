package com.hsy.likelion.LikeLionKing.post.service;

import com.hsy.likelion.LikeLionKing.post.domain.Post;
import com.hsy.likelion.LikeLionKing.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Long save(Post post) {
        return postRepository.save(post);
    }

    // 게시글 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 조회
    public List<Post> findAll(HashMap<String, String> param) {
        // size = 10(default)
        Integer size = Integer.parseInt(param.getOrDefault("size", "10"));
        if (param.containsKey("boardId")) {
            Integer categoryId = Integer.parseInt(param.get("boardId"));
            if (param.containsKey("searchKeyword")) {
                String search = param.get("searchKeyword");
                // boardId, searchKeyword
                return postRepository.findByCategorySearchAll(categoryId, search, size);
            }
            // page = 1(default)
            Integer page = Integer.parseInt(param.getOrDefault("page", "1"));
            // boardId, page
            return postRepository.findByCategoryPage(categoryId, page, size);
        }
        return null;
    }

    // 게시글 상세화면 조회
    public Optional<Post> getDetails(Long id) {
        // 조회수 증가
        increaseViews(id);
        return findById(id);
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

    // 게시글 조회수 증가
    public void increaseViews(Long id) {
        postRepository.increaseViews(id);
    }
}
