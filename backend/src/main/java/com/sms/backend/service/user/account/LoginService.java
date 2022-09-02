package com.sms.backend.service.user.account;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/2 - 14:37
 * @projectName:backend
 */
public interface LoginService {
    Map<String, String> login(String username, String password);
}
