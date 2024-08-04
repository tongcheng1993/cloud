package com.zifuji.cloud.server.business.db.webSite.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_site")
public class SiteEntity extends MyBaseEntity {
	
	
	private String path;
	
	
	

}
