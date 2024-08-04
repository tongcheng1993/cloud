package com.zifuji.cloud.server.sys.module.file.controller.qo;

import com.zifuji.cloud.server.base.db.BaseQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FilePageQo extends BaseQo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;

	private String id;
}
