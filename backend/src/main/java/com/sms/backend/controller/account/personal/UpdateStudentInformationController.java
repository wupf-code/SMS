package com.sms.backend.controller.account.personal;

import com.sms.backend.service.user.account.personal.UpdateStudentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 16:42
 * @projectName:backend
 */
@RestController
public class UpdateStudentInformationController {
    @Autowired
    private UpdateStudentInformation updateStudentInformation;
    @PostMapping("/user/account/updatastudentinformation/")
    public Map<String, String> updateStudentInformation(@RequestParam Map<String,String>data) throws ParseException {
        return updateStudentInformation.updateStudentInformation(data);
    }
}
