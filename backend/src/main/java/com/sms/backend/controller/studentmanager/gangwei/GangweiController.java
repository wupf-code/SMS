package com.sms.backend.controller.studentmanager.gangwei;

import com.alibaba.fastjson.JSONObject;
import com.sms.backend.entity.GangweiShenqing;
import com.sms.backend.pojo.gangwei_shenqing.GangweiIdState;
import com.sms.backend.service.student.GangweiShenqingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GangweiController {
    @Autowired
    private GangweiShenqingService gangweiShenqingService;
    @PostMapping("/studentmanager/gangwei/add/")
    public Map<String,String > add(@RequestParam Map<String ,String>data){
        return gangweiShenqingService.add(data);
    }
    @GetMapping("/studentmanager/gangwei/getall/")
    public JSONObject getAll(){
        return gangweiShenqingService.getAll();
    }
    @GetMapping("/studentmanager/gangwei/getinfoall/")
    public List<JSONObject> getinfo(){
        return gangweiShenqingService.getInfo();
    }
    @PostMapping("/studentmanager/gangwei/updatestate/")
    public Map<String, String> updateState(@RequestParam Map<String, String> data){
        return gangweiShenqingService.updateState(data);
    }
}
