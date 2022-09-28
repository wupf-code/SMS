package com.sms.backend.service.impl.teacher;

import com.sms.backend.mapper.GangWeiMapper;
import com.sms.backend.entity.GangWei;
import com.sms.backend.service.teacher.GangWeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GangWeiImpl implements GangWeiService {
    @Autowired
    private GangWeiMapper gangWeiMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        Map<String,String >resp = new HashMap<>();
        GangWei gangWei =new GangWei(null, data.get("xingzhi"),
                data.get("didian"),
                data.get("neirong"),
                data.get("choujin"));
        gangWeiMapper.insert(gangWei);
        resp.put("error_message","success");
        return resp;

    }
    @Override
    public List<GangWei> getAll() {
        return gangWeiMapper.selectList(null);
    }

}
