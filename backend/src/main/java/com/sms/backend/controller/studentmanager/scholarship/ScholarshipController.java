package com.sms.backend.controller.studentmanager.scholarship;

import com.sms.backend.pojo.KunnanStudent;
import com.sms.backend.service.kunnanstudent.KunnanStudentServie;
import com.sms.backend.service.scholaship.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ScholarshipController {
    @Autowired
    private ScholarshipService scholarshipService;
    @PostMapping("/studentmanager/scholarship/add/")
    public Map<String, String> kunnanStudentAdd(@RequestParam Map<String, String>data){
        return scholarshipService.addScholarship(data);
    }
    @GetMapping("/studentmanager/scholarship/getinfo/")
    public Map<String, String> getInfoById(){
        return  scholarshipService.scholarshipGetInfo();
    }

    @GetMapping("/studentmanager/scholarship/getall/")
    public List<KunnanStudent> getAll(){
        return scholarshipService.scholarshipGetAll();
    }
    @PostMapping("/studentmanager/scholarship/updateComment/")
    public Map<String, String> update(@RequestParam Map<String, String>data){
        return scholarshipService.scholarshipUpdate(data);
    }
}
