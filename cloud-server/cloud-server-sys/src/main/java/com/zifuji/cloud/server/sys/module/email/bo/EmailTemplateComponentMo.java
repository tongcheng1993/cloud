package com.zifuji.cloud.server.sys.module.email.bo;

import com.zifuji.cloud.base.bean.component.BaseComponentMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailTemplateComponentMo extends BaseComponentMo {

    private Long id;

    private LocalDateTime createTime;

    private String name;

    private String subject;
}
