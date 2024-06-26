package org.yg.memo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.yg.memo.dto.*;
import javax.servlet.*;
import org.apache.catalina.connector.RequestFacade;
import org.yg.memo.security.dto.ClubAuthMemberDTO;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
  @GetMapping({"/ex1", "/exTemplate"})
  public void ex1(){
    log.info("ex111.........edd...........");

    
  }
  
  @GetMapping({"/ex2", "/exLink"})
  public void ex2(Model model){
    
    List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(
      i -> {
        SampleDTO dto = SampleDTO.builder()
          .sno(i)
          .first("First.." + i)
          .last("Last.." + i)
          .regTime(LocalDateTime.now()).build();
        return dto;
      }).collect(Collectors.toList());
    model.addAttribute("list", list);
  }

  
  @GetMapping("/exInline")
  public String exInline(RedirectAttributes redirectAttributes){
    log.info("ex inline ....");

    SampleDTO dto = SampleDTO.builder()
      .sno(10L)
      .first("first..10")
      .last("first..10")
      .regTime(LocalDateTime.now())
      .build();

    redirectAttributes.addFlashAttribute("result", "success");
    redirectAttributes.addFlashAttribute("dto", dto);

    return "redirect:/sample/ex3";

  }

  @GetMapping("/ex3")
  public void ex3(){
    log.info("ex3");
  }

  @GetMapping({"/exLayout1", "/exSidebar"})
  public void exLayout1(){
    log.info("exLayout ..........");
  }


  //Security
  @PreAuthorize("permitAll()")
  @GetMapping("/all")
  public void exAll(){
    log.info("security all allow...........");
  }

  //로그인 된 맴버 알아보기
  @GetMapping("/member")
  @PreAuthorize("hasRole('USER')")
  public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){
    log.info("security Member allow...........");
    log.info(clubAuthMember);
  }


  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/admin")
  public void exAdmin(){
    log.info("security Admin allow...........");
  }

  //특정 사용자 에게만 접근 권한을 주고 싶을 경우
  @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq \"User15\"")
  @GetMapping("/exOnly")
  public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){
    log.info("exMember Only ....................");
    log.info(clubAuthMember);

    return "/sample/admin";
  }


}
