package com.sms.backend.service.impl.scholarship;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sms.backend.mapper.ScholarshipMapper;
import com.sms.backend.entity.Scholarship;
import com.sms.backend.entity.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.scholaship.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScholarshipImpl implements ScholarshipService {
    @Autowired
    private ScholarshipMapper scholarshipMapper;

    @Override
    public Map<String, String> addScholarship(Map<String, String> data) {
        Map<String, String> resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Scholarship scholarship = new Scholarship(null, user.getId(),user.getUsername() , data.get("reason"),"","未审核");
        scholarshipMapper.insert(scholarship);
        resp.put("error_message", "success");
        return resp;
    }

    @Override
    public Map<String, String> scholarshipGetInfo() {
        Map<String, String> resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<Scholarship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",user.getId());
        Scholarship scholarship ;
        scholarship = scholarshipMapper.selectOne(queryWrapper);
        if(scholarship != null){
            resp.put("errror_message", "success");
            resp.put("reason", scholarship.getReason());
            resp.put("state", scholarship.getState());
        }else {
            resp.put("error_message","null");
        }
        return resp;
    }

    @Override
    public List<Scholarship> scholarshipGetAll() {
        return scholarshipMapper.selectList(null);
    }

    @Override
    public Map<String, String> scholarshipUpdate(Map<String, String> data) {
        UpdateWrapper<Scholarship> updateWrapper = new UpdateWrapper<>();
        QueryWrapper<Scholarship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",(data.get("student_id")));
        Map<String,String> resp = new HashMap<>();
        Scholarship scholarship = scholarshipMapper.selectOne(queryWrapper);
        scholarship.setState("已通过");
        scholarship.setComments(data.get("comments"));
        updateWrapper.eq("student_id",data.get("student_id"));
        scholarshipMapper.update(scholarship,updateWrapper);
        resp.put("error_message", "success");
        return resp;
    }
}
