package com.pbs.likelion.LikeLionKing.service;

import com.pbs.likelion.LikeLionKing.domain.dto.PostDto;
import com.pbs.likelion.LikeLionKing.domain.Post;
import com.pbs.likelion.LikeLionKing.repository.PostRepository;

import java.util.*;

public class PostService {

    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 포스트 등록 CRUD- C
     */
    public Long register(PostDto post){
        Post postEntity = post.toEntity();
        Long id = postRepository.save(postEntity).getId();

        return id;
    }

    /**
     * 포스트 삭제 CRUD - D
     */
    public void deleteById(Long deleteId){
         postRepository.deleteById(deleteId);
    }
    /**
     * 포스트 제목 변경 CRUD - U
     */

    public void updateTitleById(String title, Long updateId){
        postRepository.updateTitleById(title, updateId);
    }

    public void updateContentById(String content, Long updateId){
        postRepository.updateContentById(content, updateId);
    }

    public void updateById(String title, String content, Long updateId){
        postRepository.updateById(title, content, updateId);
    }



    /**
     * 전체 게시글 조회
     */
    public List<PostDto> findPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto build = PostDto.builder()
                    .id(post.getId())
                    .userId(post.getUserId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .views(post.getViews())
                    .recommended(post.getRecommended())
                    .build();
            postDtos.add(build);
        }
        return postDtos;
    }




    public Optional<Post> findOne(Long postId){
        Optional<Post> findPost = postRepository.findById(postId);
        return findPost;
    }

    public List<Post> findPosts(HashMap<String, String> param) {

        // boardId 포함
        if (param.containsKey("boardId")) {
            int categoryId = Integer.parseInt(param.get("boardId"));
            //  boardId=1&page=2 (둘다 포함
            if(param.containsKey("page")){

                int page = Integer.parseInt(param.get("page"));

                return postRepository.findByCategory_WithPageNum(categoryId, page);
            }

            if (param.containsKey("searchKeyword")) {

                String searchKeyword = param.get("searchKeyword");
                return postRepository.findByCategory_WithKeyword(categoryId, searchKeyword);
            }

            return postRepository.find_LatestPosts10(categoryId);
        }


        return null;
    }
}
