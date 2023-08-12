package com.zifuji.cloud.server.sys.module.user.mo;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class LoginControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "账户名不能为空")
	private String userName;
	@NotBlank(message = "密码不能为空")
	private String passWord;
	@NotBlank(message = "")
	private String redisUuid;
	@NotBlank(message = "验证码不能为空")
	private String value;
	
}
