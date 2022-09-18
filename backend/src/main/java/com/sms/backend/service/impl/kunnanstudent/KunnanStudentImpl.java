package com.sms.backend.service.impl.kunnanstudent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sms.backend.mapper.KunnanStudentMapper;
import com.sms.backend.pojo.KunnanStudent;
import com.sms.backend.pojo.PoorStudent;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.kunnanstudent.KunnanStudentServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KunnanStudentImpl implements KunnanStudentServie {
    @Autowired
    private KunnanStudentMapper kunnanStudentMapper;
    @Override
    public Map<String, String> addKunnanStudent(Map<String, String> data) {
        Map<String, String> resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        KunnanStudent kunnanStudent = new KunnanStudent(null, user.getId(),user.getUsername() , data.get("reason"),"","未审核");
         kunnanStudentMapper.insert(kunnanStudent);
         resp.put("error_message", "success");
        return resp;
    }

    @Override
    public Map<String, String> kunnanStrudentGetInfo() {
        Map<String, String> resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<KunnanStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",user.getId());
        KunnanStudent kunnanStudent;
        kunnanStudent = kunnanStudentMapper.selectOne(queryWrapper);
        if(kunnanStudent != null){
            resp.put("errror_message", "success");
            resp.put("reason", kunnanStudent.getReason());
        }else {
            resp.put("error_message","success");
        }
        return resp;
    }

    @Override
    public List<KunnanStudent> kunnanStudentGetAll() {
        return kunnanStudentMapper.selectList(null);
    }

    @Override
    public Map<String, String> kunnanStudentUpdate(Map<String, String> data) {
        UpdateWrapper<KunnanStudent> updateWrapper = new UpdateWrapper<>();
        QueryWrapper<KunnanStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",(data.get("student_id")));
        Map<String,String> resp = new HashMap<>();
       KunnanStudent kunnanStudent = kunnanStudentMapper.selectOne(queryWrapper);
        kunnanStudent.setState("已通过");
        kunnanStudent.setComments(data.get("comments"));
        updateWrapper.eq("student_id",data.get("student_id"));
        kunnanStudentMapper.update(kunnanStudent,updateWrapper);
        resp.put("error_message", "success");
        return resp;
    }
}
