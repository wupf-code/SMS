package com.sms.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sms.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/2 - 14:27
 * @projectName:backend
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
