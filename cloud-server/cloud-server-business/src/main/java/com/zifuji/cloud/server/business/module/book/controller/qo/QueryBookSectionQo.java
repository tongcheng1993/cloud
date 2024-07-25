package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.zifuji.cloud.server.base.db.BaseQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QueryBookSectionQo extends BaseQo {

	private Long bookId;

	private String sectionName;

}
