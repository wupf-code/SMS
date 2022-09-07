package com.sms.backend.service.impl.user.account.family;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sms.backend.mapper.FamilyMapper;
import com.sms.backend.pojo.Family;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.user.account.family.GetInfoFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/7 - 19:50
 * @projectName:backend
 */
@Service
public class GetInfoFamilyImpl implements GetInfoFamilyService {
    @Autowired
    private FamilyMapper familyMapper;
    @Override
    public List<Family> getInfoFamily() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",user.getId());
        return  familyMapper.selectList(queryWrapper);
    }
}
