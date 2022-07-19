package com.study.LikeLionKing.service;

import com.study.LikeLionKing.domain.Post;
import com.study.LikeLionKing.domain.dto.PostDto;
import com.study.LikeLionKing.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Long id = postRepository.save(post.toEntity());
        return id;
    }

    public Optional<PostDto> findById(Long id){
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
        return Optional.ofNullable(postDto);
    }

    public List<PostDto> findAll(){
        List<Post> posts = new ArrayList<>();
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
            postDtos.add(postDto);
        }
        return postDtos;
    }

    public Post update(PostDto postDto){
        postRepository.update(postDto.toEntity());
        return postDto.toEntity();
    }
    public void delete(Long id){
        postRepository.delete(id);
    }
}
