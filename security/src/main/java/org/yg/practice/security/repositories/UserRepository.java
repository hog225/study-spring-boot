package org.yg.practice.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yg.practice.security.datas.entities.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findByUsername(String username);
    
}
