package com.sms.backend.controller.studentmanager;

import com.sms.backend.service.poorstudent.UpdatePoorStudentCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.MarshalledObject;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/16 - 9:43
 * @projectName:backend
 */
@RestController
public class UpdatePoorStudentCodeController {
    @Autowired
    private UpdatePoorStudentCodeService updatePoorStudentCodeService;
    @PostMapping("/studentmanager/poorstudent/update/code/")
    public Map<String, String> updatePoorStudentCode(@RequestParam Map<String, String>data){
       return  updatePoorStudentCodeService.updatePoorStudentCode(data);
    }
}
