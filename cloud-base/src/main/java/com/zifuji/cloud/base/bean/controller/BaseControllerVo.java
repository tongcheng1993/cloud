package com.zifuji.cloud.base.bean.controller;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseControllerVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateBy;

    private LocalDateTime updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long sortNum;

    private Boolean delFlag;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

}