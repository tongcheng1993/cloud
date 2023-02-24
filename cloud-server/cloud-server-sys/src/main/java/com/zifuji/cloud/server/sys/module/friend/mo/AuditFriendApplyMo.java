package com.zifuji.cloud.server.sys.module.friend.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AuditFriendApplyMo extends BaseMo {


    private Long id;

    private String groupName;

    private String noteName;



    @NotBlank(message = "")
    private String auditStatus;



}
