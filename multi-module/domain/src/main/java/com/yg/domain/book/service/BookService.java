package com.yg.domain.book.service;

import com.yg.domain.book.entity.Book;
import com.yg.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    public void insertBookAll(List<Book> book) {
        bookRepository.saveAll(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

}
