package com.sms.backend.service.scholaship;

import com.sms.backend.pojo.KunnanStudent;

import java.util.List;
import java.util.Map;

public interface ScholarshipService {
    Map<String, String> addScholarship(Map<String, String>data);
    Map<String, String> scholarshipGetInfo();
    List<KunnanStudent> scholarshipGetAll();
    Map<String, String> scholarshipUpdate(Map<String, String> data);
}
