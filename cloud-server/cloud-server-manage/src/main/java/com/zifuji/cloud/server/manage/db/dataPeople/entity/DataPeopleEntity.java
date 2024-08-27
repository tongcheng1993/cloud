package com.zifuji.cloud.server.manage.db.dataPeople.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_data_people")
public class DataPeopleEntity extends MyBaseEntity {

	private String peopleName;
}
