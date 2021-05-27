package org.yg.practice.flyway.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.yg.practice.flyway.models.User;
import org.yg.practice.flyway.repositorys.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public User create(User user){
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User read(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User update(User requestUser){
        Optional<User> optionalUser = userRepository.findById(requestUser.getId());

        return optionalUser.map(dbUser -> {
            dbUser.setAccount(requestUser.getAccount());
            dbUser.setEmail(requestUser.getAccount());
            dbUser.setPhoneNumber(requestUser.getPhoneNumber());
            dbUser.setUpdatedAt(LocalDateTime.now());
            return dbUser;
        }).map(userRepository::save).orElse(null);
    }

    public Boolean delete(Long id){
        userRepository.deleteById(id);
        return true;
    }

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
}
