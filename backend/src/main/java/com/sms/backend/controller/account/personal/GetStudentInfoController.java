package com.sms.backend.controller.account.personal;

import com.sms.backend.service.user.account.personal.GetStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 17:16
 * @projectName:backend
 */
@RestController
public class GetStudentInfoController {
    @Autowired
    private GetStudentInfoService getStudentInfoService;
    @GetMapping("/user/account/studentinfo/")
    public Map<String,String> getStudentInfo(){
        return getStudentInfoService.getStudentInfo();
    }

    @GetMapping("/studentmanager/student/getinfobyid/")
    public Map<String, String> getInfoById(@RequestParam Map<String ,String >data){
        return getStudentInfoService.getStudentInfoById(data);
    }
}
