package com.sms.backend.service.kunnanstudent;

import com.sms.backend.pojo.KunnanStudent;
import com.sms.backend.pojo.PoorStudent;

import java.util.List;
import java.util.Map;

public interface KunnanStudentServie {
    Map<String, String> addKunnanStudent(Map<String, String>data);
    Map<String, String> kunnanStrudentGetInfo();
    List<KunnanStudent> kunnanStudentGetAll();
    Map<String, String> kunnanStudentUpdate(Map<String, String> data);
}
