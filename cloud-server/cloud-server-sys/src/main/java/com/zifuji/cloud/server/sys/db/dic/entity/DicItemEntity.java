package com.zifuji.cloud.server.sys.db.dic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_dic_item")
public class DicItemEntity extends MyBaseEntity {

	private Long dicId;

	private String name = "";
	// 字典编码
	private String code = "";
	// 字典值
	private String value = "";
	// 是否展示
	private Boolean showFlag = true;
	// 是否不可以选择
	private Boolean disabled =true;

}
