package com.zifuji.cloud.server.business.module.friend.relation.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendRelationControllerVo extends BaseControllerVo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long fromBy;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long toBy;

    private String groupName;

    private String noteName;

    private String imgUrl;

}
