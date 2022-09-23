package com.sms.backend.controller.notice;

import com.sms.backend.pojo.Notice;
import com.sms.backend.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("/notice/add/")
    public Map<String, String> addNewNotice(@RequestParam Map<String, String>data){
        return noticeService.addNewNotice(data);
    }
    @GetMapping("/notice/getall/")
    public List<Notice> getAll(){
        return noticeService.getAll();
    }

    @PostMapping("/notice/update")
    public Map<String, String> update(@RequestParam Map<String, String>data){
        return noticeService.updateNewNotice(data);
    }
}
