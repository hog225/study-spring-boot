package com.yg.domain.book;

import com.yg.domain.book.entity.Book;
import com.yg.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppInit {
    private final BookService bookService;

    @PostConstruct
    private void init() {
        Book book = Book.makeBook("book1", "author1", ZonedDateTime.now());
        Book book2 = Book.makeBook("book2", "author2", ZonedDateTime.now());
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        bookService.insertBookAll(books);
    }
}
