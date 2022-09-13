package com.sms.backend.controller.account.family;

import com.sms.backend.pojo.Family;
import com.sms.backend.service.user.account.family.GetInfoFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/7 - 19:48
 * @projectName:backend
 */
@RestController
public class GetInfoFamilyController {
    @Autowired
    private GetInfoFamilyService getInfoFamilyService;

    @GetMapping("/user/account/family/getinfo/")
    public List<Family> getInfo(){
        return getInfoFamilyService.getInfoFamily();
    }
}