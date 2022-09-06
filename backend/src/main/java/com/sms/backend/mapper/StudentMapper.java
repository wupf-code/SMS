package com.sms.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sms.backend.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 16:04
 * @projectName:backend
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
