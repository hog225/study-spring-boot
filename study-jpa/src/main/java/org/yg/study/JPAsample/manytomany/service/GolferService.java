package org.yg.study.JPAsample.manytomany.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yg.study.JPAsample.manytomany.entity.GolferEntity;
import org.yg.study.JPAsample.manytomany.enums.Grade;
import org.yg.study.JPAsample.manytomany.repository.GolferRepository;

import java.util.Random;
import java.util.UUID;

import static org.yg.study.JPAsample.manytomany.service.UtilService.getRandomCountry;

@Service
@RequiredArgsConstructor
public class GolferService {
    private final GolferRepository golferRepository;

    public void createGolfer() {

        Random random = new Random();
        var age = random.nextInt(100);


        golferRepository.save(GolferEntity.builder()
                .age(age)
                .country(getRandomCountry())
                .name("golfer" + UUID.randomUUID() + age)
                .grade(Grade.getRandomGrade())
                .build());
    }


}
