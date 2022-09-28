package com.sms.backend.service.student;

import com.alibaba.fastjson.JSONObject;
import com.sms.backend.pojo.gangwei_shenqing.GangweiIdState;

import java.util.List;
import java.util.Map;

public interface GangweiShenqingService {
    Map<String, String> add(Map<String,String>data);
    JSONObject getAll();

    List<JSONObject> getInfo();

    Map<String ,String> updateState( Map<String ,String>data);
}
