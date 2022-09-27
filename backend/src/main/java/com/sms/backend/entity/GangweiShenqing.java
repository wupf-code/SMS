package com.sms.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GangweiShenqing {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer gangweiId;
    private String state;
}
