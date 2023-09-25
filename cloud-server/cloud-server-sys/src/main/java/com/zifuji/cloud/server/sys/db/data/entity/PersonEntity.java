package com.zifuji.cloud.server.sys.db.data.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_person")
public class PersonEntity extends MyBaseEntity {

	private String cardNumber;

	 private String name;
	 
	 private LocalDate birthday;
	 
	 private String sex;
	 
	 private String cardType;

}
