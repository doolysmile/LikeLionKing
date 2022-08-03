package postsite.postsitespring.domain.user.repository;

import postsite.postsitespring.domain.user.domain.User;

import java.util.List;

public interface UserRepository {

    long save(User user);
    User findById(Long id);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
}
