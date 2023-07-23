package com.zifuji.cloud.server.business.module.score.qo;

import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ScoreAccountPageQo extends BaseControllerPageQo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
}