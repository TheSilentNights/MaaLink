package com.thesilentnights.maalinkserver;

import com.thesilentnights.maalinkserver.service.MaaLinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MaaLinkSpringBootServerApplicationTests {
    @Autowired
    MaaLinkService service;

    @Test
    void contextLoads() {
        service.start();
    }

}
