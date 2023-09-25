package com.zifuji.cloud.server.base.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyBaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy = 110L;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime = LocalDateTime.now();

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy = 110L;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime = LocalDateTime.now();

    @TableField(fill = FieldFill.INSERT)
    private Integer sortNum = 0;

    @TableField(fill = FieldFill.INSERT)
    private Boolean delFlag = false;

}
