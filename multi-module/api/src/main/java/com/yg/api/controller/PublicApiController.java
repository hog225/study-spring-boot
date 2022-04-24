package com.yg.api.controller;

import com.yg.domain.book.entity.Book;
import com.yg.domain.book.service.BookService;
import com.yg.domain.pubapi.service.PublicApiService;
import com.yg.external.publicapis.dto.EntryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API")
@RequiredArgsConstructor
@Log4j2
public class PublicApiController {
    private final PublicApiService publicApiService;

    @GetMapping("/{index}")
    public ResponseEntity<EntryResponse.Entry> getBook(@PathVariable("index") Integer idx) {

        EntryResponse.Entry entry = publicApiService.getPublicApiEntry(idx);
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

}
