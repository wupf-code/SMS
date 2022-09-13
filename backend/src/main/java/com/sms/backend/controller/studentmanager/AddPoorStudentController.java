package com.sms.backend.controller.studentmanager;

import com.sms.backend.service.poorstudent.AddPoorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/13 - 20:24
 * @projectName:backend
 */
@RestController
public class AddPoorStudentController {
    @Autowired
    private AddPoorStudentService addPoorStudentService;
    @PostMapping("/studentmanager/poorstudent/add/")
    public Map<String, String> addStudent(@RequestParam Map<String,String> data) throws ParseException {
        System.out.println(data.get("family["+0+"][danwei]"));
        return addPoorStudentService.addPoorStudent(data);
    }
}
