package com.sms.backend.controller.account.personal;

import com.sms.backend.service.user.account.personal.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/7/27 - 13:40
 * @projectName:backend
 */
@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @PostMapping("/user/account/register")
    public Map<String,String> register(@RequestParam Map<String,String>map){
        String username = map.get("username");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");
        String identify = map.get("identify");
        return registerService.register(username, password, confirmedPassword,identify);
    }
}
