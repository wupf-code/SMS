package com.sms.backend.service.impl.teacher;

import com.sms.backend.mapper.TeacherLogMapper;
import com.sms.backend.pojo.TeacherLog;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherImpl implements TeacherService {
    @Autowired
    private TeacherLogMapper teacherLogMapper;
    @Override
    public Map<String, String> addLog(Map<String, String> data) {
        Map<String ,String>resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        teacherLogMapper.insert(new TeacherLog(null,
                user.getId(),
                data.get("username"),
                data.get("qudui"),
                LocalDateTime.parse(data.get("date")),
                data.get("leixing"),
                data.get("neirong")));
        resp.put("error_message","success");
        return resp;
    }
}
