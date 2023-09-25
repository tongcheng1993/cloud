package com.zifuji.cloud.server.sys.db.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_phone")
public class PhoneEntity extends MyBaseEntity {

    private String phone;

    private String name;
}
