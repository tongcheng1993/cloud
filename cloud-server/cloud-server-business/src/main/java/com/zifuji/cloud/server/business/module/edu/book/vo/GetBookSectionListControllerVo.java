package com.zifuji.cloud.server.business.module.edu.book.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class GetBookSectionListControllerVo extends BaseControllerVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long secId;

    private String secName;

}
