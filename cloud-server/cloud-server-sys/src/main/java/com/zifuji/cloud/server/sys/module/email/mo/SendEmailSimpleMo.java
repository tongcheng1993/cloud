package com.zifuji.cloud.server.sys.module.email.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SendEmailSimpleMo extends BaseMo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String to;

    private String subject;

    private String content;

    private List<Long> imgList;

    private List<Long> fileList;


}
