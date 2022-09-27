package com.sms.backend.controller.studentmanager.poorstudent;

import com.sms.backend.entity.PoorStudent;
import com.sms.backend.service.poorstudent.PoorStudentGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/15 - 21:12
 * @projectName:backend
 */
@RestController
public class PoorStudentGetAllController {
    @Autowired
    private PoorStudentGetAllService poorStudentGetAllService;
    @GetMapping("/studentmanager/poorstudent/getall/")
    public List<PoorStudent> getPoorStudentAll(){
        return poorStudentGetAllService.poorStudentGetAll();
    }
}
