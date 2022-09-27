package com.sms.backend.controller.teachermanager;

import com.sms.backend.entity.GangWei;
import com.sms.backend.service.teacher.GangWeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GangWeiController {
    @Autowired
    private GangWeiService gangWeiService;

    @PostMapping("/teachermanager/gangwei/add/")
    public Map<String,String >add(@RequestParam Map<String ,String>data){
        return gangWeiService.add(data);
    }
    @GetMapping("/teachermanager/gangwei/getall/")
    public List<GangWei> getAll(){
        return gangWeiService.getAll();
    }
}
