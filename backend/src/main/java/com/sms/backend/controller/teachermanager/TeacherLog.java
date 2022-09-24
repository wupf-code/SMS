package com.sms.backend.controller.teachermanager;

import com.sms.backend.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TeacherLog {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/teachermanager/teacher/addlog/")
    public Map<String, String > addLog(@RequestParam Map<String ,String>data){
        return teacherService.addLog(data);
    }
}
