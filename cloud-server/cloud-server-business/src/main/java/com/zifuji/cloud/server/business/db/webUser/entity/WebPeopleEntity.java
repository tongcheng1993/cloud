package com.zifuji.cloud.server.business.db.webUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_people")
@ApiModel(value = "外网个人实名信息表")
public class WebPeopleEntity extends MyBaseEntity {

    @ApiModelProperty(value = "真实姓名")
    private String peopleName;
    @ApiModelProperty(value = "真实身份证号")
    private String cardNumber;

}
