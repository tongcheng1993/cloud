package com.zifuji.cloud.server.business.module.relation.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AuditFriendApplyControllerMo  {


    private String id;

    private String groupName;

    private String noteName;



    @NotBlank(message = "")
    private String auditStatus;



}
