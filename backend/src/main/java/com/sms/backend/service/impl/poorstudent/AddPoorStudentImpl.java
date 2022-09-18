package com.sms.backend.service.impl.poorstudent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sms.backend.mapper.FamilyMapper;
import com.sms.backend.mapper.PoorStudentMapper;
import com.sms.backend.pojo.Family;
import com.sms.backend.pojo.PoorStudent;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.poorstudent.AddPoorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/13 - 21:15
 * @projectName:backend
 */
@Service
public class AddPoorStudentImpl implements AddPoorStudentService {
    @Autowired
    private PoorStudentMapper poorStudentMapper;
    @Autowired
    private FamilyMapper familyMapper;
    @Override
    public Map<String, String> addPoorStudent(Map<String, String> data) throws ParseException {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        System.out.println(data.toString());
        Map<String,String> resp =new HashMap<>();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        PoorStudent poorStudent = new PoorStudent(
                null,
                user.getId(),
                data.get("username"),
                data.get("sex"),
                data.get("phone_number"),
                data.get("school"),
                data.get("department"),
                data.get("dadui"),
                data.get("speciality"),
                data.get("id_card"),
                data.get("minzu"),
                ft.parse(data.get("birthday")),
                data.get("political_outlook"),
                data.get("address"),
                "待审核",
                "",
                0
        );
        poorStudentMapper.insert(poorStudent);
        Family family = familyMapper.selectById(user.getId());
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",user.getId());
        List<Family> families = familyMapper.selectList(queryWrapper);
        for(int i = 0 ; i < families.size();i++){
            families.get(i).setDanwei(data.get("family["+i+"][danwei]"));
            families.get(i).setSalary(data.get("family["+i+"][salary]"));
            families.get(i).setHealth(data.get("family["+i+"][health]"));
            familyMapper.updateById(families.get(i));
        }
        resp.put("error_message","success");
        return resp;
    }
}
