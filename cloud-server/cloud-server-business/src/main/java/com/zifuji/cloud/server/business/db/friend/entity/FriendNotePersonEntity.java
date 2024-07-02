package com.zifuji.cloud.server.business.db.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_friend_note_person")
public class FriendNotePersonEntity extends MyBaseEntity {

    private Long friendId;

    private String name;

    private String sex;

    private LocalDate birthday;

    private String description;
}
