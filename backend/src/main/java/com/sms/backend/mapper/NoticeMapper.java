package com.sms.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sms.backend.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
