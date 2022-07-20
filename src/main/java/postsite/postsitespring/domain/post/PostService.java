package postsite.postsitespring.domain.post;

import org.springframework.stereotype.Service;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.PostCreate;
import postsite.postsitespring.domain.post.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    // post 생성
    public long save(Post post){
        return postRepository.save(post);
    }
    // 전체 post 조회
    public List<Post> findAll(Long boardId, Long page){
        return postRepository.findAll(boardId, page);
    }
    public List<Post> findAll(Long boardId, Long page, String searchKeyword){
        return postRepository.findAll(boardId, page, searchKeyword);
    }

    // 해당 post 조회
    public Post findById(Long id){
        return postRepository.findById(id);
    }

    // post update
    public void update(Post post){
        postRepository.update(post);
    }

    //post delete
    public void delete(Long id){
        postRepository.delete(id);
    }
}
