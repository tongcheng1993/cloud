package com.zifuji.cloud.server.sys.module.email.mo;


import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveEmailTemplateControllerMo extends BaseControllerMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String subject;

    private Long templateId;

    private String content;

}
