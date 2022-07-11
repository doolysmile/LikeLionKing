package com.kch.likelion.LikeLionKing.post.service;


import com.kch.likelion.LikeLionKing.post.domain.Post;
import com.kch.likelion.LikeLionKing.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post insert(Post post){
        return postRepository.insert(post);
    }

    @Transactional
    public void update(Post post){
        postRepository.update(post);
    }

    @Transactional
    public void delete(long postId){
        postRepository.delete(postId);
    }

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }

}
