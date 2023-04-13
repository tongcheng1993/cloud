package com.zifuji.cloud.server.sys.db.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_friend_info")
public class FriendInfoEntity extends MyBaseEntity {

	private String imgUrl;

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
