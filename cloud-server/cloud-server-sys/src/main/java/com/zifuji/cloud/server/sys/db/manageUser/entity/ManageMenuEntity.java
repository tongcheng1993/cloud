package com.zifuji.cloud.server.sys.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_menu")
public class ManageMenuEntity extends MyBaseEntity {

    private Long parentId;

    private String name;

    private String path;

    private String component;

    private Integer showFlag;

    private String iconFlag;


}
