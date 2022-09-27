package com.sms.backend.controller.account.family;

import com.sms.backend.entity.Family;
import com.sms.backend.service.user.account.family.GetInfoFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/user/account/family/getinfobyid/")
    public List<Family> getInfo(@RequestParam Map<String,String> data){
        return getInfoFamilyService.getInfoFamily(data);
    }
}
