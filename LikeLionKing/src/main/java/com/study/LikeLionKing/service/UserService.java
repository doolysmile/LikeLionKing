package com.study.LikeLionKing.service;

import com.study.LikeLionKing.domain.User;
import com.study.LikeLionKing.domain.dto.UserDto;
import com.study.LikeLionKing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long save (UserDto userDto){
        Long id = userRepository.save(userDto.toEntity());
        return id;
    }

    public UserDto findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return null;
        }
        User temp = user.get();
        UserDto userDto = UserDto.builder()
                .id(temp.getId())
                .loginId(temp.getLoginId())
                .loginPw(temp.getLoginPw())
                .userRole(temp.getUserRole())
                .build();
        return userDto;
    }

    public List<UserDto> findAll(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User temp : users){
            UserDto userDto = UserDto.builder()
                    .id(temp.getId())
                    .loginId(temp.getLoginId())
                    .loginPw(temp.getLoginPw())
                    .userRole(temp.getUserRole())
                    .build();
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public User update(User user){
        userRepository.update(user);
        return user;
    }

    public void remove(Long id){
        userRepository.delete(id);
    }
}