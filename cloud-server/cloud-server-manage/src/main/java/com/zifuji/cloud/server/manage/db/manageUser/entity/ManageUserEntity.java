package com.zifuji.cloud.server.manage.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_user")
public class ManageUserEntity extends MyBaseEntity {

	private String userName = "";

	private String passWord = "";

	private String shortName = "";

}
