package org.yg.memo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    @GetMapping({"/", "/list"})
    public String list(){
        log.info("list..........");
        return "/blog/list";
    }
}
