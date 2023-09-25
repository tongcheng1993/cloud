package com.zifuji.cloud.server.sys.module.notice.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class NoticeControllerMo  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String title;

	private String author;

	private String content;
	
}
