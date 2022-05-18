package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
