package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Book;
import org.yg.study.JPAsample.repository.BookRepository;

import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BookSave {
    private final BookRepository bookRepository;

    public void save() {
        bookRepository.save(Book.builder()
                .name("eferdw_ekmkmfkewefrsdele")
                .publictionDate(ZonedDateTime.now())
                .build());
        throw new RuntimeException("bulkInsert Exception");
    }


}
