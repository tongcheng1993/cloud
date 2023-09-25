package com.zifuji.cloud.server.business.module.relation.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendRelationComponentMo  {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String groupName;

    private String name;

    private String imgUrl;
}
