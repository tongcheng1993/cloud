package com.zifuji.cloud.server.sys.module.manageUser.bo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zifuji.cloud.base.bean.BaseBo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserBo extends BaseBo {
	
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
	
	private String userName;

	private String passWord;

	private String name;
}
