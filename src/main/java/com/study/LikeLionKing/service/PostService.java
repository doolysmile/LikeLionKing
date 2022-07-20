package com.study.LikeLionKing.service;

import com.study.LikeLionKing.domain.Post;
import com.study.LikeLionKing.domain.dto.PostDto;
import com.study.LikeLionKing.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public long save(PostDto post){
        LocalDateTime now = LocalDateTime.now();
        post.setWritten(now.toString());
        Long id = postRepository.save(post.toEntity());
        return id;
    }

    public PostDto findById(Long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            return null;
        }
        Post temp = post.get();
        PostDto postDto = PostDto.builder()
                .id(temp.getId())
                .postRole(temp.getPostRole())
                .content(temp.getContent())
                .lastModified(temp.getLastModified())
                .memberId(temp.getMemberId())
                .written(temp.getWritten())
                .title(temp.getTitle())
                .recommended(temp.getRecommended())
                .views(temp.getViews())
                .build();
        return postDto;
    }

    public List<PostDto> findAll(){
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for(Post temp : posts){
            PostDto postDto = PostDto.builder()
                    .id(temp.getId())
                    .postRole(temp.getPostRole())
                    .content(temp.getContent())
                    .lastModified(temp.getLastModified())
                    .memberId(temp.getMemberId())
                    .written(temp.getWritten())
                    .title(temp.getTitle())
                    .recommended(temp.getRecommended())
                    .views(temp.getViews())
                    .build();
            System.out.println(postDto);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    public Post update(PostDto postDto){
        LocalDateTime now = LocalDateTime.now();
        postDto.setLastModified(now.toString());
        postRepository.update(postDto.toEntity());
        return postDto.toEntity();
    }
    public void delete(Long id){
        postRepository.delete(id);
    }
}
