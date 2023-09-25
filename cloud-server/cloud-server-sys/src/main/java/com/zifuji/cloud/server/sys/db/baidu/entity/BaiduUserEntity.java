package com.zifuji.cloud.server.sys.db.baidu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_baidu_user")
public class BaiduUserEntity extends MyBaseEntity {

    private String baiduUserUserName;

    private String baiduUserName;

    private String baiduUserWangpanToken;





}
