package com.zifuji.cloud.base.bean;

public class BaseConstant {

    // 系统安全参数
    public static final String KEY = "makeFun";
    public static final Long TTL = 1800000L;
    // ws 消息类型
    public static final String WS_TYPE_TOPIC = "topic";
    public static final String WS_TYPE_TOPIC_PATH_PUBLIC = "/topic/public";
    public static final String WS_TYPE_PEOPLE = "people";
    public static final String WS_TYPE_PEOPLE_PATH = "/topic/chat";
    public static final String WS_TYPE_PEOPLE_LOGOUT = "/topic/logout";

    //------------------------角色code

    public static final String ROLE_REGISTER = "register";

    public static final String ROLE_BIND_PEOPLE = "bind_people";

    public static final String ROLE_BIND_DEPT = "bind_dept";

    public static final String ROLE_BASE = "base";

    public static final String ROLE_ADMIN = "admin";

    // 系统初始化数据
    //------------------------验证码
    public static final String STRING_A = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String STRING_B = "abcdefghijklmnopqrstuvwxyz";
    public static final String STRING_C = "1234567890";
    public static final String BUSINESS_TYPE_WEB = "web";
    public static final String BUSINESS_TYPE_MANAGE = "manage";

    //------------------------判断字段字典
    public static final String CODE_START_STOP_TYPE = "start_stop_type";
    public static final String CODE_START_STOP_TYPE_1 = "start_stop_type_1";
    public static final String CODE_START_STOP_TYPE_2 = "start_stop_type_2";
    public static final String CODE_YES_NO_TYPE = "yes_no_type";
    public static final String CODE_YES_NO_TYPE_1 = "yes_no_type_1";
    public static final String CODE_YES_NO_TYPE_2 = "yes_no_type_2";
    public static final String CODE_PASS_BACK_TYPE = "pass_back_type";
    public static final String CODE_PASS_BACK_TYPE_1 = "pass_back_type_1";
    public static final String CODE_REBORN_TYPE = "reborn_type";
    public static final String CODE_REBORN_TYPE_1 = "reborn_type_1";
    public static final String CODE_REBORN_TYPE_1_VALUE = "日流水号";
    public static final String CODE_REBORN_TYPE_2 = "reborn_type_2";
    public static final String CODE_REBORN_TYPE_2_VALUE = "月流水号";
    public static final String CODE_REBORN_TYPE_3 = "reborn_type_3";
    public static final String CODE_REBORN_TYPE_3_VALUE = "年流水号";
    public static final String CODE_REBORN_TYPE_4 = "reborn_type_4";
    public static final String CODE_REBORN_TYPE_5_VALUE = "不限时流水号";


    // redis缓存key前缀
    public static final String REDIS_WEBSOCKET = "redis_websocket_";
    public static final String REDIS_SEQ = "redis_seq_";

}
