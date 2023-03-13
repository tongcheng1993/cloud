package com.zifuji.cloud.server.sys.db.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_unit")
public class UnitEntity extends MyBaseEntity {


    private String unitNumber;

    private String name;



}
