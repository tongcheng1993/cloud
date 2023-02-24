package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class LoginMo extends BaseMo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "")
	private String userName;
	@NotBlank(message = "")
	private String passWord;
	@NotBlank(message = "")
	private String redisUuid;
	@NotBlank(message = "")
	private String value;
	
}
