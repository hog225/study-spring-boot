package yg.study.studyspringbatch.domain.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void dbInsert() {
        Book book = Book.create("33", ZonedDateTime.now());
        bookRepository.save(book);
    }

}