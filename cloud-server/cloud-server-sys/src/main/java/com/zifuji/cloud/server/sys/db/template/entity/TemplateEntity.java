package com.zifuji.cloud.server.sys.db.template.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_template")
public class TemplateEntity extends MyBaseEntity {

    private String content;

}
