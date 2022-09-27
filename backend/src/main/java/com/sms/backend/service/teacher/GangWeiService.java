package com.sms.backend.service.teacher;

import com.sms.backend.entity.GangWei;

import java.util.List;
import java.util.Map;

public interface GangWeiService {
    Map<String, String> add(Map<String,String>data);
    List<GangWei> getAll();
}
