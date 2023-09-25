package com.zifuji.cloud.server.sys.module.email.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SendEmailSimpleControllerMo  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String to;

    private String subject;

    private String content;

    private List<String> imgList;

    private List<String> fileList;


}
