package com.zifuji.cloud.server.business.db.webUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_company")
@ApiModel(value = "外网单位实名信息表")
public class WebCompanyEntity extends MyBaseEntity {


    private String companyName;

    private String deptCode;
}
