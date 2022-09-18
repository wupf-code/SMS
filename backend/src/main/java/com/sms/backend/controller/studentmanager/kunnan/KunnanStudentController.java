package com.sms.backend.controller.studentmanager.kunnan;

import com.sms.backend.service.kunnanstudent.KunnanStudentServie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class KunnanStudentController {
    @Autowired
    private KunnanStudentServie kunnanStudentServie;
    @PostMapping("/studentmanager/kunnanstudent/add/")
    public Map<String, String> kunnanStudentAdd(@RequestParam Map<String, String>data){
        return kunnanStudentServie.addKunnanStudent(data);
    }
    @GetMapping("/studentmanager/kunnanstudent/getinfo/")
    public Map<String, String> getInfoById(){
        return  kunnanStudentServie.kunnanStrudentGetInfo();
    }
}
