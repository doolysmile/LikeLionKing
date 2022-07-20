package postsite.postsitespring.domain.user;

import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.UserCreate;
import postsite.postsitespring.domain.user.dto.UserUpdate;
import postsite.postsitespring.domain.user.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public long createMember(User user){
        return userRepository.save(user);
    }

    public List<User> allMembers(){
        return userRepository.findAll();
    }

    public User oneMember(Long id){
        return userRepository.findById(id);
    }

    public void updateMember(User user){
        userRepository.update(user);
    }

    public void deleteMember(Long id){
        userRepository.delete(id);
    }
}
