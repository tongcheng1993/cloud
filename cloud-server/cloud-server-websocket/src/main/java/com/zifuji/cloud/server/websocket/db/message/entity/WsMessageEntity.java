package com.zifuji.cloud.server.websocket.db.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_ws_message")
public class WsMessageEntity extends MyBaseEntity {

    private String msContent;

}
