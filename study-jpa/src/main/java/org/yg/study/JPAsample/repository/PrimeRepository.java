package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.PrimeEntity;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface PrimeRepository extends JpaRepository<PrimeEntity, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM PrimeEntity e WHERE e.id = :id")
    Optional<PrimeEntity> findByKey(@Param("id") String id);
}
