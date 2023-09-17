package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.PrimeEntity;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;
import org.yg.study.JPAsample.repository.PrimeRepository;

import javax.persistence.LockModeType;

@RequiredArgsConstructor
@Service
public class PrimeService {
    private final PrimeRepository primeRepository;


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public PrimeEntity modifyOrCreateService(String key, String name) {
        var primeEntity = primeRepository.findByKey(key).orElse(null);
        if(primeEntity == null) {
            primeEntity = PrimeEntity.builder()
                    .id(key)
                    .name(name)
                    .build();

            System.out.println("INSERT-------");
        } else {
            primeEntity.setName(name);
            System.out.println("UPDATE-------");
        }

        return primeRepository.save(primeEntity);
    }



}
