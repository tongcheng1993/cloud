package com.zifuji.cloud.server.manage.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_menu")
public class ManageMenuEntity extends MyBaseEntity {

	private Long parentId = -1L;

	private String label = "";

	private String name = "";

	private String path = "";

	private String component = "";

	private Boolean showFlag = false;

	private String icon = "";

}
