package com.sms.backend.service.user.account.personal;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/2 - 15:24
 * @projectName:backend
 */
public interface RegisterService {
    Map<String,String> register(String username, String password, String confirmedPassword, String identify);

}
