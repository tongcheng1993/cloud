package com.zifuji.cloud.server.sys.module.email.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SendEmailTemplateControllerMo  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String to;

    private String id;

    private String paramMapStr;

    private List<String> imgList;

    private List<String> fileList;

}
