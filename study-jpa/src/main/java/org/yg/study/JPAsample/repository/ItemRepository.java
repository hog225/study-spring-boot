package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
