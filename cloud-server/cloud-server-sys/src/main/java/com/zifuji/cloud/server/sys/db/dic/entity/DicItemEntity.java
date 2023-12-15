package com.zifuji.cloud.server.sys.db.dic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_dic_item")
public class DicItemEntity extends MyBaseEntity {

    private Long dicId;
    // 字典编码
    private String itemCode = "";
    // 字典值
    private String itemValue = "";
    // 是否展示
    private Boolean showFlag = true;
    // 是否可以选择
    private Boolean checkFlag = true;

}
