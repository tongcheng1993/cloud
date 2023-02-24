package com.zifuji.cloud.server.sys.module.email.mo;


import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveEmailTemplateMo extends BaseMo {
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
