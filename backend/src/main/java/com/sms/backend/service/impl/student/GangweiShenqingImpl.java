package com.sms.backend.service.impl.student;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sms.backend.entity.GangWei;
import com.sms.backend.mapper.GangWeiMapper;
import com.sms.backend.mapper.GangweiShenqingMapper;
import com.sms.backend.entity.GangweiShenqing;
import com.sms.backend.entity.User;
import com.sms.backend.mapper.UserMapper;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GangWeiMapper gangWeiMapper;
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

    @Override
    public List<JSONObject> getInfo() {
        List<GangweiShenqing> gangweiShenqingList = gangweiShenqingMapper.selectList(null);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for(GangweiShenqing gangweiShenqing : gangweiShenqingList){
            User user =  userMapper.selectById(gangweiShenqing.getUserId());
            GangWei gangWei = gangWeiMapper.selectById(gangweiShenqing.getGangweiId());
            JSONObject obj = new JSONObject();
            obj.put("id",gangweiShenqing.getId());
            obj.put("user",user);
            obj.put("gangwei",gangWei);
            obj.put("state",gangweiShenqing.getState());
            jsonObjectList.add(obj);
        }
        return jsonObjectList;
    }

    @Override
    public Map<String, String> updateState(Map<String, String> data) {
        Map<String, String> resp = new HashMap<>();
        QueryWrapper<GangweiShenqing>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",data.get("id"));
        GangweiShenqing gangweiShenqing = gangweiShenqingMapper.selectOne(queryWrapper);
        gangweiShenqing.setState(data.get("state"));
        gangweiShenqingMapper.updateById(gangweiShenqing);
        resp.put("error_message","success");
        return resp;
    }

}
