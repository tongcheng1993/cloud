package com.zifuji.cloud.server.business.module.user.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GetMenuControllerVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sortNum;

    private Boolean delFlag;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    private String name;

    private String path;

    private String component;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer showFlag;

    private String iconFlag;


}
