package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.entity.Writer;

public interface WriterRepository extends JpaRepository<Writer, Long> {
}
