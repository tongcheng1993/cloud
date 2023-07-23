package com.zifuji.cloud.server.business.module.friend.relation.bo;

import com.zifuji.cloud.base.bean.component.BaseComponentMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendComponentMo extends BaseComponentMo {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private Long updateBy;
	
	private LocalDateTime updateTime;
	
	private Long sortNum;
	
	private Boolean delFlag;

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
