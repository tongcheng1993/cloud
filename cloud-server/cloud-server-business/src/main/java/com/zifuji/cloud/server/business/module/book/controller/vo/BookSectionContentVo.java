package com.zifuji.cloud.server.business.module.book.controller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.server.base.db.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BookSectionContentVo  extends BaseControllerVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long sectionId;

    private String sectionContent;

}
