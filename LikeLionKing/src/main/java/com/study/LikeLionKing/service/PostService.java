package com.study.LikeLionKing.service;

import com.study.LikeLionKing.domain.Post;
import com.study.LikeLionKing.repository.PostRepository;
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

    public long save(Post post){
        Long id = postRepository.save(post);
        return id;
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post update(Post post){
        postRepository.update(post);
        return post;
    }
    public void delete(Long id){
        postRepository.delete(id);
    }
}
