package com.sms.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KunnanStudent {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private String username;
    private String reason;
    private String comments;
    private String state;
}
