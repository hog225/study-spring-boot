package org.yg.practice.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app-admin")
public class AdminController {

    @GetMapping("get/{val}")
    ResponseEntity<String> get(@PathVariable Long val){
        return new ResponseEntity("val = " + val, HttpStatus.OK);
    }

}
