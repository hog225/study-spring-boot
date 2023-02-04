package org.yg.dumserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
public class SimpleController {

    @RequestMapping("/locaus")
    public String locaus() {
        log.info("locaus-server-1");
        return "locaus-server-1";
    }

}
