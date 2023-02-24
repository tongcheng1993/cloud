package com.zifuji.cloud.server.sys.module.email.bo;

import com.zifuji.cloud.base.bean.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailRecordBo extends BaseBo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String addrFrom;

    private String addrTo;

    private String subject;

    private String content;

    private String sendStatus;

    private LocalDateTime sendTime;

}
