package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.yg.study.JPAsample.entity.Writer;

import org.yg.study.JPAsample.repository.WriterRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WriterService {
    private final WriterRepository writerRepository;


    void bulkInsert(List<Writer> writers) {
        writerRepository.saveAll(writers);


    }

    void updateWriter(String name) {

    }

}
