package com.sms.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/4 - 15:56
 * @projectName:backend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String sex;
    private String phoneNumber;
    private String idCard;
    private Date birthday;
    private String address;
    private String politicalOutlook;
    private String bedroom;
}
