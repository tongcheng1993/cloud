package com.zifuji.cloud.server.sys.module.user.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebDrawCaptchaControllerVo extends BaseControllerVo {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String redisUuid;

    private byte[] imgBytes;
}