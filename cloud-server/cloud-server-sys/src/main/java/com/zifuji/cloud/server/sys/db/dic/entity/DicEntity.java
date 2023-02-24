package com.zifuji.cloud.server.sys.db.dic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_dic")
public class DicEntity extends MyBaseEntity {
	
	

	// 字典名字
	private String name="";
	// 字典编码
	private String code="";
	// 描述
	private String description="";	
	
}
