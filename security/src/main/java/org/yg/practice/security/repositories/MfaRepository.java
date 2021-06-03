package org.yg.practice.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yg.practice.security.datas.entities.MfaEntity;

@Repository
public interface MfaRepository extends JpaRepository<MfaEntity, Long> {
    
    MfaEntity findByUsername(String username);
    
}
