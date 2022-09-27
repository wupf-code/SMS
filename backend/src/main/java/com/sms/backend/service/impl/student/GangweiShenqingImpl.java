package com.sms.backend.service.impl.student;

import com.alibaba.fastjson.JSONObject;
import com.sms.backend.mapper.GangweiShenqingMapper;
import com.sms.backend.entity.GangweiShenqing;
import com.sms.backend.entity.User;
import com.sms.backend.pojo.gangwei_shenqing.GangweiIdState;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.student.GangweiShenqingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class GangweiShenqingImpl implements GangweiShenqingService {
    @Autowired
    private GangweiShenqingMapper gangweiShenqingMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        Map<String,String>resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        GangweiShenqing gangweiShenqing = new GangweiShenqing(
                null,
                user.getId(),
               Integer.parseInt(data.get("gangwei_id")),
                "未审核"
        );
        gangweiShenqingMapper.insert(gangweiShenqing);
        resp.put("error_message","success");
        return resp;
    }

    @Override
    public JSONObject getAll() {
        List<GangweiShenqing> gangweiShenqings =  gangweiShenqingMapper.selectList(null);
        JSONObject obj = new JSONObject();
        for(GangweiShenqing gangweiShenqing : gangweiShenqings){
            obj.put(gangweiShenqing.getGangweiId().toString(),gangweiShenqing.getState());
        }
        return obj;
    }
}
