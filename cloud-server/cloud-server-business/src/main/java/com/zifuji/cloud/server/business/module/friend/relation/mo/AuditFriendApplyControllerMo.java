package com.zifuji.cloud.server.business.module.friend.relation.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AuditFriendApplyControllerMo extends BaseControllerMo {


    private Long id;

    private String groupName;

    private String noteName;



    @NotBlank(message = "")
    private String auditStatus;



}