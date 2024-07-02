package com.zifuji.cloud.server.sys.module.email.controller.mo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveEmailTemplateMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String subject;

    private Long templateId;

    private String content;

}
