package org.yg.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload-test")
public class UploadTestController {
    @GetMapping("/uploadEx")
    public void uploadEx(){
        System.out.println("Upload");
    }
}
