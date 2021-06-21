package org.yg.memo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yg.memo.dto.BlogDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.service.BlogService;

@Controller
@RequestMapping("/blog")
@Slf4j
@RequiredArgsConstructor
public class BlogController {
    private final BlogService service;

    @GetMapping("/")
    public String index(){
        return "redirect:/blog/list";
    }

    @GetMapping("/list")
    //아래 @ModelAttribute는 없어도 되는 것 같다.
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list " + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register(){
        log.info("reg get");
    }

    @PostMapping("/register")
    public String registerPost(BlogDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto..." + dto);
        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/blog/list";
    }

    @GetMapping({"/read", "/modify"})
    //아래 @ModelAttribute는 없어도 되는 것 같다.
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("gno " + gno);

        model.addAttribute("dto", service.read(gno));

    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){
        log.info("gno..." + gno);
        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/blog/list";
    }

    @PostMapping("/modify")
    public String modify(BlogDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify ...............");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());
        return "redirect:/blog/read";

    }


}
