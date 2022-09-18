package com.sms.backend.controller.studentmanager.poorstudent;

import com.sms.backend.service.poorstudent.PoorStudentGetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/14 - 9:42
 * @projectName:backend
 */
@RestController
public class PoorStudentGetInfoController {
    @Autowired
    private PoorStudentGetInfoService poorStudentGetInfoService;
    @GetMapping("/studentmanager/poorstudent/getinfo/")
    Map<String, String> poorStudentGetInfo(){
        return poorStudentGetInfoService.poorStudentGetInfo();
    }

    @GetMapping("/studentmanager/poorstudent/getinfobyid/")
    Map<String, String> getInfoById(@RequestParam Map<String ,String>data){
        return  poorStudentGetInfoService.poorStudentGetById(data);
    }
}
