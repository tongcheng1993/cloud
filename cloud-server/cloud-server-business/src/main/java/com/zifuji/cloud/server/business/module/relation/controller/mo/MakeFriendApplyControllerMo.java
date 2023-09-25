package com.zifuji.cloud.server.business.module.relation.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class MakeFriendApplyControllerMo  {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotNull(message = "另一人标识不能为空")
    @ApiModelProperty(value = "另一人userId")
    private Long toBy;
    @NotBlank(message = "备注名称不能为空")
    @ApiModelProperty(value = "备注名称")
    private String noteName;
    @ApiModelProperty(value = "分组名称")
    private String groupName;
    @ApiModelProperty(value = "验证信息")
    private String content;
}
