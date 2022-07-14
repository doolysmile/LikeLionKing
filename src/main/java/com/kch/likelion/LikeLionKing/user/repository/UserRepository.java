package com.kch.likelion.LikeLionKing.user.repository;

import com.kch.likelion.LikeLionKing.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    void update(User user);

    void delete(long userId);

    List<User> findAll();

}
