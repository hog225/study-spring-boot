package org.yg.practice.flyway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yg.practice.flyway.daos.BookDao;
import org.yg.practice.flyway.models.*;

@RestController
@RequestMapping(value="/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = "/create")
    public Book create() {
        Book book = new Book();
        book.setId(1L);
        book.setName("concur");
        book.setAuthor("yg");

        bookDao.save(book);

        return book;
    }
    
}