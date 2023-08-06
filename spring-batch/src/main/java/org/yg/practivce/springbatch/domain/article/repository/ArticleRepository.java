package org.yg.practivce.springbatch.domain.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.practivce.springbatch.domain.article.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
