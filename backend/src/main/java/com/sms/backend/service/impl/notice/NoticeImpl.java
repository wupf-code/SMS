package com.sms.backend.service.impl.notice;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sms.backend.mapper.NoticeMapper;
import com.sms.backend.pojo.Notice;
import com.sms.backend.pojo.User;
import com.sms.backend.service.impl.utils.UserDetailsImpl;
import com.sms.backend.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoticeImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Map<String, String> addNewNotice(Map<String, String> data) {
        Map<String, String> resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Notice notice = new Notice(null,user.getId(),user.getUsername(),data.get("title"),data.get("content"));
        noticeMapper.insert(notice);
        resp.put("error_message","success");
        return resp;
    }

    @Override
    public List<Notice> getAll() {
        List<Notice> resp =  noticeMapper.selectList(null);
        Collections.sort(resp, (a,b)->b.getId()-a.getId());
        return resp;
    }

    @Override
    public Map<String, String> updateNewNotice(Map<String, String> data) {
        Map<String, String> resp = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Notice notice = new Notice(Integer.parseInt(data.get("id")),user.getId(),user.getUsername(),data.get("title"),data.get("content"));
        noticeMapper.updateById(notice);
        resp.put("error_message","success");
        return resp;
    }
}
