package com.zifuji.cloud.server.sys.db.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_friend_relation")
public class FriendRelationEntity extends MyBaseEntity {

    private Long fromBy;

    private Long toBy;

    private String groupName;

    private String noteName;


}
