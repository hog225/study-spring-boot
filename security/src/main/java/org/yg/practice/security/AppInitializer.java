package org.yg.practice.security;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yg.practice.security.datas.entities.UserEntity;
import org.yg.practice.security.repositories.UserRepository;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AppInitializer {
    private final UserRepository userRepository;
//security!#34
    @PostConstruct
    private void init(){
        UserEntity user = UserEntity.builder()
                .username("admin")
                .password("$2a$10$15oAo5ObvO9YRimyxUMzROMt2Ga73mXDp9D/Ern6w6gE50cmf/9IG")
                .roles("admin").build();
        userRepository.save(user);
        UserEntity user2 = UserEntity.builder()
                .username("member")
                .password("$2a$10$15oAo5ObvO9YRimyxUMzROMt2Ga73mXDp9D/Ern6w6gE50cmf/9IG")
                .roles("member").build();
        userRepository.save(user2);
        UserEntity user3 = UserEntity.builder()
                .username("super")
                .password("$2a$10$15oAo5ObvO9YRimyxUMzROMt2Ga73mXDp9D/Ern6w6gE50cmf/9IG")
                .roles("member,admin").build();
        userRepository.save(user3);
    }

}
