package com.qlzxsyzx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_role")
@Data
public class Role {
    @TableId
    private Integer roleId;

    private String roleName;

    private String description;
}
