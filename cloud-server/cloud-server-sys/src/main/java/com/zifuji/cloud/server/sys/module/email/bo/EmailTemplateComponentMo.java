package com.zifuji.cloud.server.sys.module.email.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailTemplateComponentMo {

    private String id;

    private LocalDateTime createTime;

    private String name;

    private String subject;
}
