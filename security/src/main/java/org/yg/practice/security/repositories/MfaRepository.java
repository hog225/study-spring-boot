package org.yg.practice.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yg.practice.security.datas.entities.Mfa;

@Repository
public interface MfaRepository extends JpaRepository<Mfa, Long> {
    
    Mfa findByUsername(String username);
    
}
