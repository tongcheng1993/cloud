package com.zifuji.cloud.server.websocket.db.active.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_ws_active")
public class WsActiveEntity extends MyBaseEntity {


    private String activeType;
}
