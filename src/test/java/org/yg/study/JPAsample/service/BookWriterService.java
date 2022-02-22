package org.yg.study.JPAsample.service;

import org.hibernate.jpa.spi.JpaCompliance;
import org.hibernate.tool.schema.ast.SqlScriptParserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.yg.study.JPAsample.entity.Book;
import org.yg.study.JPAsample.entity.Writer;
import org.yg.study.JPAsample.repository.BookRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;



@SpringBootTest
@ActiveProfiles("sql")
class BookWriterService {

    @Autowired
    private BookService bookService;

    @Autowired
    private WriterService writerService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    // bulk insert 미동작
    void bookServiceTest() {
        List<Book> books = new ArrayList<>();
        IntStream.range(0, 3).forEach(num -> {
            books.add(Book.create("yg" + num, ZonedDateTime.now().minusDays(num)));
        });
        bookService.bulkInsert(books);
    }

    @Test
    // bulk insert 동작
    void writerService() {
        List<Writer> writers = new ArrayList<>();
        IntStream.range(0, 3).forEach(num -> {
            writers.add(Writer.create("yg" + num));
        });
        writerService.bulkInsert(writers);
    }

    @Test
    @Transactional // 있어야 동작
    @Rollback(value = false) // 없으면 update query 안 보이지만  transaction 실재 이루어 진다.
    void dirtyChecking1() {
       bookService.updateBook("e13332223mkrmkmkfmekf");
       System.out.println(bookRepository.getById(1L).getName());
    }

    @Test
    void jdbcTemplateTest() {
        System.out.println(bookService.queryUsingJdbcTemplate(2L));
    }

}