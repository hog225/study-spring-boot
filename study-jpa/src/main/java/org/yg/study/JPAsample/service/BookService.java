package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.ast.SqlScriptParserException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Book;
import org.yg.study.JPAsample.repository.BookRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final JdbcTemplate jdbcTemplate;

    public void bulkInsert(List<Book> books) {
        bookRepository.saveAll(books);
    }


    @Transactional
    public void updateBook(String bookName){
        Book book = bookRepository.findById(1L).orElseThrow(() -> new SqlScriptParserException("efe"));
        System.out.println(book);
        book.setName(bookName);
//        bookRepository.save(book);
//        List<Book> books = bookRepository.findAll();
//        books.get(1).setName("yg");

    }

    @Transactional
    public void updateBookWithId(Long id, String bookName){
        Book book = bookRepository.findById(id).orElseThrow(() -> new SqlScriptParserException("efe"));
        System.out.println(book);
        book.setName(bookName);

    }

    public String queryUsingJdbcTemplate(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject("SELECT name FROM book where seq = " + id, String.class);
    }

    public void upupdateBook(String bookName) {
        updateBook(bookName);
    }


}
