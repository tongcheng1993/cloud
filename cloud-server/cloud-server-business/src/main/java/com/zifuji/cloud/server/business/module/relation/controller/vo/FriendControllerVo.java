package com.zifuji.cloud.server.business.module.relation.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendControllerVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("")
	private String id;
	@ApiModelProperty("")
	private String createBy;
	@ApiModelProperty("")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;
	@ApiModelProperty("")
	private String updateBy;
	@ApiModelProperty("")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updateTime;
	@ApiModelProperty("")
	private String sortNum;
	@ApiModelProperty("")
	private Boolean delFlag;
	@ApiModelProperty("")
	private String parentId;

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
