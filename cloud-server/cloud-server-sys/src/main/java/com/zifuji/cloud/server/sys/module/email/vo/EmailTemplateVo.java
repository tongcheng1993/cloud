package com.zifuji.cloud.server.sys.module.email.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailTemplateVo extends BaseVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private LocalDateTime createTime;

    private String name;

    private String subject;

    private String content;
}
