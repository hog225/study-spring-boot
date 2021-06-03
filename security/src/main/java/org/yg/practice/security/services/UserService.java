package org.yg.practice.security.services;

import org.yg.practice.security.datas.entities.User;
// import org.yg.practice.security.repositories.UserRepository;

public interface UserService {
    
    
    public User getUser(String username);

    public User getUser(User user);
}
