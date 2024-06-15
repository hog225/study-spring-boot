package org.yg.study.JPAsample.manytomany.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.yg.study.JPAsample.manytomany.GolferTestConfig;

@SpringBootTest
@ActiveProfiles("local")
class GolferServiceTest {

    @Autowired
    private GolferService golferService;

    @Test
    public void golferCreateTest() {
        golferService.createGolfer();
    }

}