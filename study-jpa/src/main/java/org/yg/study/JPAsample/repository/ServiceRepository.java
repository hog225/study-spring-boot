package org.yg.study.JPAsample.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.yg.study.JPAsample.entity.Book;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceEntity, ServiceKey> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ServiceEntity> findByKeyServiceIdAndKeyServiceType(String serviceId, String serviceType);


}
