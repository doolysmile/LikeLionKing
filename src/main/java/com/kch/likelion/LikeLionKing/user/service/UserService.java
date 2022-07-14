package com.kch.likelion.LikeLionKing.user.service;

import com.kch.likelion.LikeLionKing.user.domain.User;
import com.kch.likelion.LikeLionKing.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User insert(User user){
        return userRepository.insert(user);
    }

    @Transactional
    public void update(User user){
        userRepository.update(user);
    }

    @Transactional
    public void delete(long userId){
        userRepository.delete(userId);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
