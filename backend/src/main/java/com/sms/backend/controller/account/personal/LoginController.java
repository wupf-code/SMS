package com.sms.backend.controller.account.personal;

import com.sms.backend.service.user.account.personal.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/2 - 14:34
 * @projectName:backend
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/token/")
    public Map<String, String> getToken(@RequestParam Map<String, String>map){
        String username = map.get("username");
        String password = map.get("password");
        return loginService.login(username, password);
    }
}
