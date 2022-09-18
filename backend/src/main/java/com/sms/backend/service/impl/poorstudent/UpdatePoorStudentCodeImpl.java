package com.sms.backend.service.impl.poorstudent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sms.backend.mapper.PoorStudentMapper;
import com.sms.backend.pojo.PoorStudent;
import com.sms.backend.pojo.Student;
import com.sms.backend.service.poorstudent.UpdatePoorStudentCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/16 - 9:32
 * @projectName:backend
 */
@Service
public class UpdatePoorStudentCodeImpl implements UpdatePoorStudentCodeService {


    @Autowired
    private PoorStudentMapper poorStudentMapper;
    //length用户要求产生字符串的长度
    public  String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public Map<String, String> updatePoorStudentCode(Map<String, String> data) {
        UpdateWrapper<PoorStudent> updateWrapper = new UpdateWrapper<>();
        QueryWrapper<PoorStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",(data.get("student_id")));
        Map<String,String> resp = new HashMap<>();
        PoorStudent poorStudent = poorStudentMapper.selectOne(queryWrapper);
        poorStudent.setShenhe("已通过");
        poorStudent.setLevel(Integer.valueOf(data.get("level")));
        String code = getRandomString(20);
        poorStudent.setCode(code);
        updateWrapper.eq("student_id",data.get("student_id"));
        poorStudentMapper.update(poorStudent,updateWrapper);
        resp.put("error_message", "success");
        return resp;
    }
}
