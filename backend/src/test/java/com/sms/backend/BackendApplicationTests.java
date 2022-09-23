package com.sms.backend;

import com.sms.backend.service.notice.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private NoticeService noticeService;
    @Test
    void contextLoads() {

        System.out.println(noticeService.getAll());
    }

}
