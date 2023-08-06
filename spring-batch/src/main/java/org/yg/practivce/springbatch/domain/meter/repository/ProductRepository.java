package org.yg.practivce.springbatch.domain.meter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.practivce.springbatch.domain.meter.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
