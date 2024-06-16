package org.yg.study.JPAsample.manytomany.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.manytomany.entity.GearEntity;
import org.yg.study.JPAsample.manytomany.enums.GearType;
import org.yg.study.JPAsample.manytomany.repository.GearRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@Log4j2
@Service
@RequiredArgsConstructor
public class GearService {
    private final GearRepository gearRepository;

    private String getRandomUUID() {
        var uuid = UUID.randomUUID().toString();
        log.info("Generated UUID: {}", uuid);
        return UUID.randomUUID().toString();
    }

    @Transactional
    public List<GearEntity> createGears(int size) {
        var entities = IntStream.range(0, size).mapToObj(i ->
            GearEntity.builder()
                    .gearId(getRandomUUID())
                    .name("gear" + i)
                    .gearType(GearType.getRandomGearType())
                    .clubNumber((int) (Math.random() * 10))
                    .build()
        ).collect(Collectors.toSet());

        return gearRepository.saveAll(entities);
    }
}
