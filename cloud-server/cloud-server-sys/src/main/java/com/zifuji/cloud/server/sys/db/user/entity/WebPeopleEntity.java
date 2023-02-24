package com.zifuji.cloud.server.sys.db.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_people")
@ApiModel(value = "外网个人实名信息表")
public class WebPeopleEntity extends MyBaseEntity {

    @ApiModelProperty(value = "真实姓名")
    private String peopleName;
    @ApiModelProperty(value = "真实身份证号")
    private String cardNumber;

}
