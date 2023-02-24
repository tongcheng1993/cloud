package com.zifuji.cloud.server.sys.db.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_demo")
public class DemoEntity extends MyBaseEntity {

    private String name="";
}
