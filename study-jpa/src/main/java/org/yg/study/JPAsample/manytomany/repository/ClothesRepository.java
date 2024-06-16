package org.yg.study.JPAsample.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.manytomany.entity.ClothesEntity;
import org.yg.study.JPAsample.manytomany.entity.GearEntity;

public interface ClothesRepository extends JpaRepository<ClothesEntity, Long> {
}
