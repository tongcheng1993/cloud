package com.zifuji.cloud.server.business.module.user.service.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WebMenuBo {

    private Long id;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;

    private Long sortNum;

    private Boolean delFlag;

    private Long parentId;

    private String name;

    private String path;

    private String component;

    private Integer showFlag;

    private String iconFlag;


}
