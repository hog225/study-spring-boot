package org.yg.practice.security.service;

import org.yg.practice.security.datas.entities.User;
// import org.yg.practice.security.repositories.UserRepository;

public interface UserService {
    
    
    public User getUser(String username);

    public User getUser(User user);
}
