package org.yg.study.JPAsample.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.manytomany.entity.GolferGearEntity;
import org.yg.study.JPAsample.manytomany.entity.GolferGearEntityId;

public interface GolferGearRepository extends JpaRepository<GolferGearEntity, GolferGearEntityId> {

}
