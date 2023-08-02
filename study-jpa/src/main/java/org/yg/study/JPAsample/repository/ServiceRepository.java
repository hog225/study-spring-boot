package org.yg.study.JPAsample.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.yg.study.JPAsample.entity.Book;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;

import javax.persistence.LockModeType;
import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, ServiceKey> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<ServiceEntity> findByKeyServiceId(String serviceId);

}
