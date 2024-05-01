package org.yg.study.JPAsample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.yg.study.JPAsample.config.TestConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("sql")
class BookSilentRollbackServiceTest {
    @Autowired
    private BookSilentRollbackService bookSilentRollbackService;
    @Test
    void bookSilentRollbackServiceInsertTest() {
        bookSilentRollbackService.insert();
    }

}