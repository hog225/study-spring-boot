package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.yg.study.JPAsample.entity.Writer;

import org.yg.study.JPAsample.repository.WriterRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class WriterService {
    private final WriterRepository writerRepository;


    void bulkInsert(List<Writer> writers) {
        writerRepository.saveAll(writers);
        log.info("WRITE");
    }

    void updateWriter(String name) {

    }

}
