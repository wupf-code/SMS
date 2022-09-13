package com.sms.backend.service.impl.user.account.family;

import com.sms.backend.mapper.FamilyMapper;
import com.sms.backend.pojo.Family;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.user.account.family.AddFamilyMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/7 - 15:40
 * @projectName:backend
 */
@Service
public class AddFamilyMemberImpl implements AddFamilyMember {
    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public Map<String, String> addFamilyMember(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        System.out.println(data.toString());
        Map<String,String> resp =new HashMap<>();
        Family family = new Family(null,
                user.getId(),
                data.get("username"),
                Integer.parseInt(data.get("age")),
                data.get("sex"),
                data.get("relation"),
                data.get("occupation"),
                data.get("xueli"),
                data.get("state"),
                data.get("weixin"),
                data.get("email"),
                "",
                "",
                ""
                );
        familyMapper.insert(family);
        resp.put("error_message", "success");
        return resp;
    }
}
