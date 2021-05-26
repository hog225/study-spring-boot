package org.yg.practice.flyway.controllers;
import org.springframework.web.bind.annotation.*;

@RestController()
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "hello flyway";
    }
    
}