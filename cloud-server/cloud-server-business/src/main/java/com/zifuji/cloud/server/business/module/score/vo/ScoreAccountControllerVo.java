package com.zifuji.cloud.server.business.module.score.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ScoreAccountControllerVo extends BaseControllerVo {

	private static final long serialVersionUID = 1L;

	
	
	private Integer scoreNum;
	

	
}
