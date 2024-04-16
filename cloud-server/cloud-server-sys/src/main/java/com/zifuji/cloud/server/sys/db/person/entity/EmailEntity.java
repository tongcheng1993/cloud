package com.zifuji.cloud.server.sys.db.person.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_email")
public class EmailEntity extends MyBaseEntity {

    private String email;

    private String name;
}
