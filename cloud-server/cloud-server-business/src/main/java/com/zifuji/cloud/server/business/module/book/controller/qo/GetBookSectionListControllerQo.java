package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Valid
@ApiModel
public class GetBookSectionListControllerQo extends Page {
    @NotNull(message = "")
    private String id;
}
