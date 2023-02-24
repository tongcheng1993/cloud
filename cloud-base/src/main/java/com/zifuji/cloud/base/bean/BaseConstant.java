package com.zifuji.cloud.base.bean;

public class BaseConstant {

	// 系统安全参数
	public static final String KEY="woaimengyue";
	public static final String VISIT_KEY="woaiwangmengyue,woyaozuowangmengyuedetiangou";
	
	public static final Long TTL=1800000L;
	
	
	// 系统初始化数据
	//------------------------登录
	public static final String STRING_A="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String STRING_B="abcdefghijklmnopqrstuvwxyz";
	public static final String STRING_C="1234567890";
	public static final String BUSINESS_TYPE_WEB="web";
	
	public static final String BUSINESS_TYPE_MANAGE="manage";
	//------------------------角色
	public static final String ROLE_VISIT="visit";
	
	public static final String ROLE_REGISTER="register";

<<<<<<< HEAD
	public static final String ROLE_BIND_PEOPLE="bind_people";

	public static final String ROLE_BIND_DEPT="bind_dept";

=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
	public static final String ROLE_BASE="base";

	
	public static final String ROLE_ADMIN="admin";
	//------------------------判断字段字典
	public static final String CODE_START_STOP_TYPE ="start_stop_type";
	public static final String CODE_START_STOP_TYPE_1 ="start_stop_type_1";
	public static final String CODE_START_STOP_TYPE_2 ="start_stop_type_2";
	public static final String CODE_YES_NO_TYPE ="yes_no_type";
	public static final String CODE_YES_NO_TYPE_1 ="yes_no_type_1";
	public static final String CODE_YES_NO_TYPE_2 ="yes_no_type_2";
	public static final String CODE_PASS_BACK_TYPE ="pass_back_type";
	public static final String CODE_PASS_BACK_TYPE_1 ="pass_back_type_1";
	public static final String CODE_REBORN_TYPE="reborn_type";
	public static final String CODE_REBORN_TYPE_1="reborn_type_1";
	public static final String CODE_REBORN_TYPE_1_VALUE="日流水号";
	public static final String CODE_REBORN_TYPE_2="reborn_type_2";
	public static final String CODE_REBORN_TYPE_2_VALUE="月流水号";
	public static final String CODE_REBORN_TYPE_3="reborn_type_3";
	public static final String CODE_REBORN_TYPE_3_VALUE="年流水号";
	public static final String CODE_REBORN_TYPE_4="reborn_type_4";
	public static final String CODE_REBORN_TYPE_5_VALUE="不限时流水号";
	
	
	
	// redis缓存key前缀
	public static final String REDIS_WEBSOCKET="redis_websocket_";
	public static final String REDIS_SEQ="redis_seq_";
	
}
