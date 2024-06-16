package org.yg.study.JPAsample.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.manytomany.entity.GearEntity;

public interface GearRepository extends JpaRepository<GearEntity, Long> {
}
