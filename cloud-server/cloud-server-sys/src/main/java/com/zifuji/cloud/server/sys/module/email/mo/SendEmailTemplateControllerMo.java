package com.zifuji.cloud.server.sys.module.email.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SendEmailTemplateControllerMo extends BaseControllerMo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String to;

    private Long id;

    private String paramMapStr;

    private List<Long> imgList;

    private List<Long> fileList;

}
