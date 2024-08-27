package com.zifuji.cloud.server.business.db.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_site")
public class SiteEntity extends MyBaseEntity {
	
	
	private String name;
	
	private String title;
	
	private String note;
	
	private String path;
	
	
	

}
