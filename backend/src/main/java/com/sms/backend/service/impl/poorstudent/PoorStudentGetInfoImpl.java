package com.sms.backend.service.impl.poorstudent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sms.backend.mapper.PoorStudentMapper;
import com.sms.backend.pojo.PoorStudent;
import com.sms.backend.pojo.Student;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.poorstudent.PoorStudentGetInfoService;
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
 * @date:2022/9/14 - 9:22
 * @projectName:backend
 */
@Service
public class PoorStudentGetInfoImpl implements PoorStudentGetInfoService {
    @Autowired
    private PoorStudentMapper poorStudentMapper;
    @Override
    public Map<String, String> poorStudentGetInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<PoorStudent> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("student_id",user.getId());
        PoorStudent poorStudent = poorStudentMapper.selectOne(queryWrapper);
        Map<String, String> map =new HashMap<>();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        if(poorStudent!=null) {
            map.put("error_message", "success");
            map.put("username", poorStudent.getUsername());
            map.put("sex", poorStudent.getSex());
            map.put("phone_number", poorStudent.getPhoneNumber());
            map.put("school", poorStudent.getSchool());
            map.put("department", poorStudent.getDepartment());
            map.put("dadui", poorStudent.getDadui());
            map.put("speciality", poorStudent.getZhuanye());
            map.put("id_card", poorStudent.getIdNumber());
            map.put("minzu", poorStudent.getMinzu());
            map.put("birthday", ft.format(poorStudent.getBirthday()));
            map.put("political_outlook", poorStudent.getState());
            map.put("address", poorStudent.getAddress());
            map.put("shenhe", poorStudent.getShenhe());
            map.put("level", poorStudent.getLevel().toString());
        }else {
            map.put("error_message","暂无信息");
        }
        return map;
    }
}
