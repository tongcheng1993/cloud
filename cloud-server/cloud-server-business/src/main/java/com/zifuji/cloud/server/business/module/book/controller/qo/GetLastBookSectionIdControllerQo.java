package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class GetLastBookSectionIdControllerQo extends Page {
    private String id;
}