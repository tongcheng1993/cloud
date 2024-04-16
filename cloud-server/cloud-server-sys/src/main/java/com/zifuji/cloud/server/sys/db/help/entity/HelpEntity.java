package com.zifuji.cloud.server.sys.db.help.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_help")
public class HelpEntity extends MyBaseEntity {

    private String name;

    private String content;

}
