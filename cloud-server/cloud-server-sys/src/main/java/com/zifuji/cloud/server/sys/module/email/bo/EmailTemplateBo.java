package com.zifuji.cloud.server.sys.module.email.bo;

import com.zifuji.cloud.base.bean.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailTemplateBo extends BaseBo {

    private Long id;

    private LocalDateTime createTime;

    private String name;

    private String subject;
}
