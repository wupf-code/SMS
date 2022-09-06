package com.sms.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sms.backend.mapper.StudentMapper;
import com.sms.backend.pojo.Student;
import com.sms.backend.service.user.account.UpdateStudentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 16:02
 * @projectName:backend
 */
@Service
public class UpdateStudentInformationImpl implements UpdateStudentInformation {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Map<String, String> updateStudentInformation(Map<String, String> data) throws ParseException {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        Map<String,String> resp = new HashMap<>();
        Student student = new Student();
        student.setUsername(data.get("username"));
        student.setSex(data.get("sex"));
        student.setPhoneNumber(data.get("phone_number"));
        student.setIdCard(data.get("id_card"));
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

        student.setBirthday(ft.parse(data.get("birthday")));
        student.setAddress(data.get("address"));
        student.setPoliticalOutlook(data.get("political_outlook"));
        student.setBedroom(data.get("bedroom"));
        updateWrapper.eq("username",data.get("username"));
        studentMapper.update(student,updateWrapper);
        resp.put("error_message", "success");
        return resp;
    }
}
