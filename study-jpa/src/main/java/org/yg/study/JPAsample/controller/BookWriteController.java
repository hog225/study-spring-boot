package org.yg.study.JPAsample.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.repository.MemberRepository;
import org.yg.study.JPAsample.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookWriteController {
    private final BookService bookService;

    //http://localhost:8080/book/1/name/effee
    //dirty checking
    @PostMapping("/book/{id}/name/{name}")
    public String findMember(@PathVariable("id") Long id, @PathVariable("name") String name){
        bookService.updateBookWithId(id, name);
        return name;
    }
}
