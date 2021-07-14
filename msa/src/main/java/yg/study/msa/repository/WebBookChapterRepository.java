package yg.study.msa.repository;

import yg.study.msa.model.entity.WebBookChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebBookChapterRepository extends JpaRepository<WebBookChapter,Long> {
}
