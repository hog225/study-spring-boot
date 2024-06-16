package org.yg.study.JPAsample.manytomany.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.config.TestConfig;
import org.yg.study.JPAsample.manytomany.GolferTestConfig;
import org.yg.study.JPAsample.manytomany.repository.GearRepository;
import org.yg.study.JPAsample.manytomany.repository.GolferGearRepository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ActiveProfiles({"sql", "test"})
class GolferServiceTest {

    @Autowired
    private GolferService golferService;

    @Autowired
    private GearService gearService;

    @Autowired
    private GearRepository gearRepository;

    @Autowired
    private GolferGearRepository golferGearRepository;

    @Autowired
    EntityManager em;

    @Test
    public void golferCreateTest() {
        golferService.createGolfer();
    }

    @Test
    @Transactional
    @Rollback(false)
    public void golferCreateWithGears() {
        var gears = gearService.createGears(3);
        em.flush();
        em.clear();

        golferService.createGolfer(new HashSet<>(gears), Collections.emptySet());
        em.flush();
        em.clear();


        golferGearRepository.findAll().forEach(System.out::println);
        gearRepository.findAll().forEach(System.out::println);


    }

}