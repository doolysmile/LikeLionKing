package lionStudy.lionStudy.repository;

import lionStudy.lionStudy.domain.DTO.PostDto;
import lionStudy.lionStudy.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(PostDto post);
    Optional<Post> findById(Long id);
    List<Post> findAll();

    void deleteById(Long id);

    void updateById(String title, String content, Long id);
    void updateTitleById(String title, Long id);

    void updateContentById(String content, Long id);







}
