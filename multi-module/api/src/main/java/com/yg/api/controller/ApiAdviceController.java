package com.yg.api.controller;

import com.yg.api.request.BookGetRequestWithHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/writers")
@RequiredArgsConstructor
@Log4j2
public class ApiAdviceController {
    @ModelAttribute("bookGetRequestWithHeader")
    public BookGetRequestWithHeader addSomething(@RequestHeader(value="KEY", required = true) String key) {
        return BookGetRequestWithHeader.builder().key(key).build();
    }
//https://stackoverflow.com/questions/36295095/how-to-decorate-all-requests-to-take-a-value-from-header-and-add-it-in-the-body 이거 시도해 보자 
    @GetMapping("/allTest3")
    public ResponseEntity<BookGetRequestWithHeader> getAllBookWithHeader(
            @Valid BookGetRequestWithHeader bookGetRequestWithHeader
    ) {
        log.info("{} {} {}", bookGetRequestWithHeader.getCategory(), bookGetRequestWithHeader.getBookName(), bookGetRequestWithHeader.getWriter());
        return new ResponseEntity<>(bookGetRequestWithHeader, HttpStatus.OK);
    }

}
