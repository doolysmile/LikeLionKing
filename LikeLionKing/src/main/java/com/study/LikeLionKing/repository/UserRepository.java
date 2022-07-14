package com.study.LikeLionKing.repository;

import com.study.LikeLionKing.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Long save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
}