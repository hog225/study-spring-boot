package org.yg.practice.flyway.repositorys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yg.practice.flyway.models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    

    
}