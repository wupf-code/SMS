package com.sms.backend.service.impl.poorstudent;

import com.sms.backend.mapper.PoorStudentMapper;
import com.sms.backend.pojo.PoorStudent;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.poorstudent.PoorStudentGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/15 - 21:06
 * @projectName:backend
 */
@Service
public class PoorStudentGetAllImpl implements PoorStudentGetAllService {
    @Autowired
    private PoorStudentMapper poorStudentMapper;
    @Override
    public List<PoorStudent> poorStudentGetAll() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        return  poorStudentMapper.selectList(null);
    }
}
