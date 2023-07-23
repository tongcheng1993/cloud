package com.zifuji.cloud.server.business.module.friend.relation.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendInfoControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "")
	private String imgUrl;
	
	@NotBlank(message = "")
	private String name;

	@NotBlank(message = "")
	private String sex;

	@NotNull(message = "")
	private LocalDate birthday;
	
	@NotBlank(message = "")
	private String birthProvince;
	
	@NotBlank(message = "")
	private String birthCity;
	
	@NotBlank(message = "")
	private String birthTown;
	
	@NotBlank(message = "")
	private String birthAddr;

	@NotBlank(message = "")
	private String province;
	
	@NotBlank(message = "")
	private String city;
	
	@NotBlank(message = "")
	private String town;
	
	@NotBlank(message = "")
	private String addr;
	
	@NotBlank(message = "")
	private String info;

}
