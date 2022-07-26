package lionStudy.lionStudy.service;

import lionStudy.lionStudy.domain.DTO.MemberDto;
import lionStudy.lionStudy.domain.DTO.PostDto;
import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.domain.Post;
import lionStudy.lionStudy.repository.MemberRepository;
import lionStudy.lionStudy.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        postRepository.save(postEntity).getId();

        return post.getId();
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
}
