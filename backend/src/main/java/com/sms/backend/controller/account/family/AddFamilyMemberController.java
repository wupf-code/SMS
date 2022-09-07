package com.sms.backend.controller.account.family;

import com.sms.backend.service.user.account.family.AddFamilyMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/7 - 15:49
 * @projectName:backend
 */
@RestController
public class AddFamilyMemberController {
    @Autowired
    private AddFamilyMember addFamilyMember;
    @PostMapping("/user/account/family/add")
    public Map<String, String> addFamilyMember(@RequestParam Map<String, String> data){
        return addFamilyMember.addFamilyMember(data);
    }
}
