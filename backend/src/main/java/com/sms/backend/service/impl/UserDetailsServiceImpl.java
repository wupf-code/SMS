package com.sms.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.sms.backend.mapper.UserMapper;
import com.sms.backend.entity.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/7/21 - 12:55
 * @projectName:backend
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw new RuntimeException("用户不存在");
        }

        return new UserDetailsImpl(user);
    }
}
