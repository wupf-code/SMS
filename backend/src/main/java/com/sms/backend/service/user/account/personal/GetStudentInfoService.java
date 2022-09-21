package com.sms.backend.service.user.account.personal;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 17:02
 * @projectName:backend
 */
public interface GetStudentInfoService {
    Map<String,String> getStudentInfo();
    Map<String,String> getStudentInfoById(Map<String, String>data);
}
