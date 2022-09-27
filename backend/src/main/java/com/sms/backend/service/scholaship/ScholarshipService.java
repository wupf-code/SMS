package com.sms.backend.service.scholaship;

import com.sms.backend.entity.Scholarship;

import java.util.List;
import java.util.Map;

public interface ScholarshipService {
    Map<String, String> addScholarship(Map<String, String>data);
    Map<String, String> scholarshipGetInfo();
    List<Scholarship> scholarshipGetAll();
    Map<String, String> scholarshipUpdate(Map<String, String> data);
}
