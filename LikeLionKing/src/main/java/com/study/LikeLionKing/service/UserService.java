package com.study.LikeLionKing.service;

import com.study.LikeLionKing.dto.User;
import com.study.LikeLionKing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long save (User user){
        Long id = userRepository.save(user);
        return id;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User update(User user){
        userRepository.update(user);
        return user;
    }

    public void remove(Long id){
        userRepository.delete(id);
    }
}