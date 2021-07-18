package org.yg.memo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yg.memo.dto.NoteDTO;
import org.yg.memo.service.NoteService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/notes/")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteservice;

    @PostMapping(value = "")
    public ResponseEntity<Long> register(@RequestBody NoteDTO noteDTO){
        log.info("----register ---- ");
        log.info(noteDTO);

        Long num = noteservice.register(noteDTO);
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDTO> read(@PathVariable("num") Long num){
        log.info("----get ---- ");
        log.info(num);

        return new ResponseEntity<>(noteservice.get(num), HttpStatus.OK);
    }

    //이메일을 가진 회원이 작성한 모든 Note 조회
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteDTO>> getList(String email){
        log.info("----get ---- ");
        log.info(email);

        return new ResponseEntity<>(noteservice.getAllWithWriter(email), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("num") Long num){
        log.info("----remove ---- ");
        log.info(num);

        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

    @PutMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody NoteDTO noteDTO){
        log.info("----modify ---- ");
        log.info(noteDTO);

        noteservice.modify(noteDTO);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }
}
