package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;
import org.yg.study.JPAsample.entity.Writer;
import org.yg.study.JPAsample.repository.ServiceRepository;
import org.yg.study.JPAsample.repository.WriterRepository;

import javax.persistence.LockModeType;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;


    @Transactional
    public ServiceEntity modifyOrCreateService(ServiceKey key, String name) {
        ServiceEntity serviceEntity = serviceRepository.findById(key).orElse(null);
        if(serviceEntity == null) {
            serviceEntity = ServiceEntity.builder()
                    .key(key)
                    .name(name)
                    .build();
        } else {
            serviceEntity.setName(name);
        }
        return serviceRepository.save(serviceEntity);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ServiceEntity modifyOrCreateServiceReadUncommitted(ServiceKey key, String name) {
        ServiceEntity serviceEntity = serviceRepository.findById(key).orElse(null);
        if(serviceEntity == null) {
            serviceEntity = ServiceEntity.builder()
                    .key(key)
                    .name(name)
                    .build();
        } else {
            serviceEntity.setName(name);
        }
        return serviceRepository.save(serviceEntity);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public ServiceEntity modifyOrCreateServiceReadUncommittedPropagationNew(ServiceKey key, String name) {
        ServiceEntity serviceEntity = serviceRepository.findById(key).orElse(null);
        if(serviceEntity == null) {
            serviceEntity = ServiceEntity.builder()
                    .key(key)
                    .name(name)
                    .build();
        } else {
            serviceEntity.setName(name);
        }
        return serviceRepository.save(serviceEntity);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ServiceEntity modifyOrCreateServiceeSerializable(ServiceKey key, String name) {
        ServiceEntity serviceEntity = serviceRepository.findById(key).orElse(null);
        if(serviceEntity == null) {
            serviceEntity = ServiceEntity.builder()
                    .key(key)
                    .name(name)
                    .build();
        } else {
            serviceEntity.setName(name);
        }
        return serviceRepository.save(serviceEntity);
    }


    @Transactional
    public ServiceEntity modifyOrCreateServiceShareLock(String serviceId, String serviceType, String name) {
        ServiceEntity serviceEntity = serviceRepository.findByKeyServiceIdAndKeyServiceType(serviceId, serviceType)
                .stream().findFirst().orElse(null);
        if(serviceEntity == null) {
            serviceEntity = ServiceEntity.builder()
                    .key(new ServiceKey(serviceId, serviceType))
                    .name(name)
                    .build();
        } else {
            serviceEntity.setName(name);
        }
        return serviceRepository.save(serviceEntity);
    }


}
