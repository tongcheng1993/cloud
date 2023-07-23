package com.zifuji.cloud.server.sys.module.email.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailRecordControllerVo extends BaseControllerVo {

    private LocalDateTime createTime;

    private String addrFrom;

    private String addrTo;

    private String subject;

    private String content;

    private String fileIdList;

    private String sendStatus;

    private LocalDateTime sendTime;
}
