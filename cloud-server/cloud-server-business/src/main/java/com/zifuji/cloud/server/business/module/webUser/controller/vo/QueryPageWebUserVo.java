package com.zifuji.cloud.server.business.module.webUser.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPageWebUserVo {

    private String id;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "账户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String passWord;
    @ApiModelProperty(value = "绑定邮箱")
    private String email;
    @ApiModelProperty(value = "绑定手机号")
    private String phone;
    @ApiModelProperty(value = "账号类型 1 个人 2 单位")
    private String type;
    @ApiModelProperty(value = "实名认证")
    private Long peopleId;
    @ApiModelProperty(value = "单位账户")
    private Long companyId;








}
