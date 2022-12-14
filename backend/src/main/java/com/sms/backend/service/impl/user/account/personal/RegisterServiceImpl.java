package com.sms.backend.service.impl.user.account.personal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.sms.backend.mapper.StudentMapper;
import com.sms.backend.mapper.UserMapper;
import com.sms.backend.entity.Student;
import com.sms.backend.entity.User;
import com.sms.backend.service.user.account.personal.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/7/27 - 13:20
 * @projectName:backend
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword,String identify) {
        Map<String, String> map = new HashMap<>();
        if(username == null){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password == null || confirmedPassword == null){
            map.put("error_message", "密码不能为空");
            return map;
        }
        username = username.trim();
        if(username.length() == 0){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if (password.length() == 0 || confirmedPassword.length() == 0){
            map.put("error_message","密码长度不能为空");
            return map;
        }
        if (username.length()>100){
            map.put("error_message","用户名长度不能大于100");
            return map;
        }
        if (password.length()>100 || confirmedPassword.length() > 100){
            map.put("error_message","密码长度不能大于100");
            return map;
        }



        if(password.equals(confirmedPassword)==false){
            map.put("error_message", "两次密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User>  users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null,username, encodedPassword, identify);
        userMapper.insert(user);
        Student student =new Student(null, username,"", "","", new Date(),"","","");

        if(identify.equals("student")){
            studentMapper.insert(student);
        }
        map.put("error_message","success");
        return map;
    }
}
