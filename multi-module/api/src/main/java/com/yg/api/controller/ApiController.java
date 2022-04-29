package com.yg.api.controller;

import com.yg.api.request.*;
import com.yg.api.validated.ValidationGroups;
import com.yg.domain.book.entity.Book;
import com.yg.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Log4j2
public class ApiController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        System.out.println("get book ");
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping()
    public void createBook(@Valid BookCreateRequest request) {
        log.info(request);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook(@Valid BookGetRequest request) {
        log.info("{} {} {}", request.getCategory(), request.getBookName(), request.getWriter());
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/allTest1")
    public ResponseEntity<BookGetSelfValidRequest> getAllBookWithDefaultRequest(@Valid BookGetSelfValidRequest request) {
        log.info("{} {} {}", request.getCategory(), request.getBookName(), request.getWriter());
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    /**
     *
     * @param request
     * @return
     * BookName 을 NotEmpty 같은걸로 가지고 있는 프로퍼티에 대해서만 validation 작업을 수행
     */
    @GetMapping("/allTest2")
    public ResponseEntity<BookGetRequestValidated> getAllBookValidated(
            @Validated(ValidationGroups.BookName.class) BookGetRequestValidated request
    ) {
        log.info("{} {} {}", request.getCategory(), request.getBookName(), request.getWriter());
        return new ResponseEntity<>(request, HttpStatus.OK);
    }



}
