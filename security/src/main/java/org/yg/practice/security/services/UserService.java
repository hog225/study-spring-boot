package org.yg.practice.security.services;

import org.yg.practice.security.datas.entities.UserEntity;
// import org.yg.practice.security.repositories.UserRepository;

public interface UserService {
    
    
    public UserEntity getUser(String username);

    public UserEntity getUser(UserEntity user);
}
