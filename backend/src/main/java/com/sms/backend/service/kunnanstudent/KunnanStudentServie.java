package com.sms.backend.service.kunnanstudent;

import com.sms.backend.entity.KunnanStudent;

import java.util.List;
import java.util.Map;

public interface KunnanStudentServie {
    Map<String, String> addKunnanStudent(Map<String, String>data);
    Map<String, String> kunnanStrudentGetInfo();
    List<KunnanStudent> kunnanStudentGetAll();
    Map<String, String> kunnanStudentUpdate(Map<String, String> data);
}
