package com.zifuji.cloud.server.business.module.search.controller.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SearchVo {

	private String name;
	
	private String title;
	
	private String note;
	
	private String path;
	
}
