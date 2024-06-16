package org.yg.study.JPAsample.manytomany.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.manytomany.entity.*;
import org.yg.study.JPAsample.manytomany.enums.Grade;
import org.yg.study.JPAsample.manytomany.repository.GolferRepository;

import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.yg.study.JPAsample.manytomany.service.UtilService.getRandomAge;
import static org.yg.study.JPAsample.manytomany.service.UtilService.getRandomCountry;

@Service
@RequiredArgsConstructor
public class GolferService {
    private final GolferRepository golferRepository;

    @Transactional
    public void createGolfer() {

        int age = getRandomAge();


        golferRepository.save(GolferEntity.builder()
                .age(age)
                .country(getRandomCountry())
                .name("golfer" + UUID.randomUUID() + age)
                .grade(Grade.getRandomGrade())
                .build());
    }

    @Transactional
    public void createGolfer(Set<GearEntity> gears, Set<ClothesEntity> clothesList) {

        int age = getRandomAge();

        var entity = GolferEntity.builder()
                .age(age)
                .country(getRandomCountry())
                .name("golfer" + UUID.randomUUID() + age)
                .grade(Grade.getRandomGrade())
                .build();
        var savedEntity = golferRepository.save(entity);

        var golferGears = gears.stream().map(gear -> GolferGearEntity.builder().golfer(savedEntity).gear(gear).build()).collect(Collectors.toSet());
        entity.addGolferGears(golferGears);
        var golferClothes = clothesList.stream().map(clothes -> GolferClothesEntity.builder().golfer(savedEntity).clothes(clothes).build()).collect(Collectors.toSet());
        entity.addGolferClothes(golferClothes);
        golferRepository.save(entity);
    }


}
