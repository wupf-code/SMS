package com.sms.backend.service.notice;

import com.sms.backend.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    Map<String, String> addNewNotice(Map<String, String>data);
    List<Notice> getAll();
    Map<String, String> updateNewNotice(Map<String, String>data);
}
