package com.yg.external.publicapis.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("external")
class PublicApiTest {
    @Autowired
    private PublicApi publicApi;

    @Test
    public void getEntriesTest() {
        System.out.println(this.publicApi.getEntries());
    }

}