package com.sms.backend.service.poorstudent;

import java.text.ParseException;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/13 - 21:13
 * @projectName:backend
 */
public interface AddPoorStudentService {
    Map<String,String> addPoorStudent(Map<String, String> data) throws ParseException;
}
