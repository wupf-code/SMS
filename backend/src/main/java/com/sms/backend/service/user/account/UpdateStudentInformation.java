package com.sms.backend.service.user.account;

import java.text.ParseException;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 16:00
 * @projectName:backend
 */
public interface UpdateStudentInformation {
     Map<String, String> updateStudentInformation(Map<String, String> data) throws ParseException;
}
