package com.sms.backend.service.poorstudent;

import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/14 - 9:24
 * @projectName:backend
 */
public interface PoorStudentGetInfoService {
    Map<String, String> poorStudentGetInfo();
    Map<String ,String> poorStudentGetById(Map<String, String >data);

}
