package com.zifuji.cloud.server.sys.db.area.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_area")
public class AreaEntity extends MyBaseEntity {
    // 双亲节点
    private Long parentId = 0L;
    // 区域类型 国家country 省province 市city 区town
    private String type = "";
    // 名称
    private String name = "";
    // 全称
    private String realName = "";
    // 编码
    private String code = "";

}
