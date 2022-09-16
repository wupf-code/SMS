package com.sms.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 武鹏飞
 * @user:ASUS
 * @date:2022/9/13 - 21:01
 * @projectName:backend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoorStudent {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private String username;
    private String sex;
    private String phoneNumber;
    private String school;
    private String department;
    private String dadui;
    private String zhuanye;
    private String idNumber;
    private String minzu;
    private Date birthday;
    private String state;
    private String address;
    private String shenhe;
    private String code;
}