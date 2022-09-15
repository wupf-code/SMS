package com.sms.backend.service.impl.user.account.personal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sms.backend.mapper.StudentMapper;
import com.sms.backend.pojo.Student;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.user.account.personal.GetStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 17:03
 * @projectName:backend
 */
@Service
public class GetStudentInfoImpl implements GetStudentInfoService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Map<String, String> getStudentInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<Student>queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        Student student = studentMapper.selectOne(queryWrapper);
        Map<String, String> map =new HashMap<>();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        if(student!=null) {
            System.out.println("1");
            map.put("error_message", "success");
            map.put("username", student.getUsername());
            map.put("sex", student.getSex());
            map.put("phone_number", student.getPhoneNumber());
            map.put("id_card", student.getIdCard());
            map.put("birthday", (ft.format(student.getBirthday())));
            map.put("address", student.getAddress());
            map.put("political_outlook", student.getPoliticalOutlook());
            map.put("bedroom", student.getBedroom());
        }else {
            System.out.println("2");
            map.put("error_message","暂无信息");
        }
        return map;
    }
}
