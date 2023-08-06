package org.yg.practivce.springbatch.domain.meter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.practivce.springbatch.domain.meter.Meter;

public interface MeterRepository extends JpaRepository<Meter, Long> {
}
