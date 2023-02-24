package com.zifuji.cloud.server.sys.module.friend.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private String name;

	private String sex;

	private LocalDate birthday;

	private String birthProvince;

	private String birthCity;

	private String birthTown;

	private String birthAddr;

	private String province;

	private String city;

	private String town;

	private String addr;

	private String info;
	
}
