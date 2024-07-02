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
public class BookSectionVo extends BaseControllerVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long bookId;

    private String sectionName;

    private String sectionContent;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long nextId;

}
