package postsite.postsitespring.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public long createMember(User user){
        return userRepository.save(user);
    }

    public List<User> allMembers(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void updateMember(User user){
        userRepository.update(user);
    }

    public void deleteMember(Long id){
        userRepository.delete(id);
    }
}
