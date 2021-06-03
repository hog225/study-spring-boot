package org.yg.practice.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yg.practice.security.datas.entities.*;
import org.yg.practice.security.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public UserEntity getUser(String username){
        return getUser(UserEntity.builder().username(username).build());
    }

    @Override
    public UserEntity getUser(UserEntity user){
        return userRepository.findByUsername(user.getUsername());
    }
}
