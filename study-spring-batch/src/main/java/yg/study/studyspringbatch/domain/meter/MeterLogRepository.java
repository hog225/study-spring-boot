package yg.study.studyspringbatch.domain.meter;

import org.springframework.data.jpa.repository.JpaRepository;
import yg.study.studyspringbatch.domain.target.TargetEntity;

public interface MeterLogRepository extends JpaRepository<MeterLogEntity, Long> {

}
