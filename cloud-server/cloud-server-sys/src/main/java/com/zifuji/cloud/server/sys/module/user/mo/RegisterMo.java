package com.zifuji.cloud.server.sys.module.user.mo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.base.bean.BaseMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class RegisterMo extends BaseMo {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "账户名不能为空")
	private String userName;
	@NotBlank(message = "密码不能为空")
	private String passWord;
	@NotBlank(message = "确认密码不能为空")
	private String passWordSec;
	@NotBlank(message = "昵称不能为空")
	private String name;
	@NotBlank(message = "图片验证码唯一标识不能为空")
	private String redisUuid;
	@NotBlank(message = "验证码不能为空")
	private String value;
}
