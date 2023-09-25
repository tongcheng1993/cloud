package com.zifuji.cloud.server.business.db.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_friend_ms")
public class FriendMsEntity extends MyBaseEntity {

}
