package com.sms.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/7 - 15:29
 * @projectName:backend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private String username;
    private Integer age;
    private String sex;
    private String relation;
    private String occupation;
    private String xueli;
    private String state;
    private String weixin;
    private String email;
}
