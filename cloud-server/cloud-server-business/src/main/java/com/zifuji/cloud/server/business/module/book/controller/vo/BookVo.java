package com.zifuji.cloud.server.business.module.book.controller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.server.base.db.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BookVo extends BaseVo {


    private String bookName;

    private String bookAuth;

    private String bookType;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long bookImg;


    private List<BookSectionVo> bookSectionVoList;



}
