package com.zifuji.cloud.server.sys.module.email.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailRecordPageQo  extends Page {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String addrTo;
}
