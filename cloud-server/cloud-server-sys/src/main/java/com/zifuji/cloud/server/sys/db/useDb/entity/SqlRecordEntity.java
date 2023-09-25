package com.zifuji.cloud.server.sys.db.useDb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_sql_record")
@ApiModel(value = "sql操作记录表")
public class SqlRecordEntity extends MyBaseEntity {

    private String sqlType;

    private String sqlNote;

    private Boolean sqlResult;

}
