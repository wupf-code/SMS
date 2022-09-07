package com.sms.backend.service.impl.user.account.personal;

import com.sms.backend.pojo.User;

import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.user.account.personal.LoginService;
import com.sms.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/2 - 14:39
 * @projectName:backend
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    public Map<String, String> login(String username, String password){
        //加密
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        //验证吗，如果验证失败，自动处理异常
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //验证成功后取出用户
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();
        //封装为jwt token
        String jwt = JwtUtil.createJWT(user.getId().toString());
        //处理返回值
        Map<String, String> map = new HashMap<>();
        map.put("error_message","success");
        map.put("token",jwt);
        return map;
    }

}
