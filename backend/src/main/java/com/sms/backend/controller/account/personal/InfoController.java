package com.sms.backend.controller.account.personal;


import com.sms.backend.service.user.account.personal.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/7/27 - 12:49
 * @projectName:backend
 */
@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/user/account/info/")
    public Map<String, String> getinfo(){
        return infoService.getInfo();
    }

}
