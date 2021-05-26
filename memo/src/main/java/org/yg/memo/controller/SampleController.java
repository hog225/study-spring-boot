package org.yg.memo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.yg.memo.dto.*;
import javax.servlet.*;
import org.apache.catalina.connector.RequestFacade;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
  @GetMapping("/ex1")
  public void ex1(){
    log.info("ex1...................");
    
  }
  
  @GetMapping("/ex2")
  public void ex2(Model model){
    
    List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(
      i -> {
        SampleDTO dto = SampleDTO.builder()
          .sno(i)
          .first("First.." + i).last("Last.." + i).regTime(LocalDateTime.now()).build();
        return dto;
      }).collect(Collectors.toList());
    model.addAttribute("list", list);
  }
  


}
