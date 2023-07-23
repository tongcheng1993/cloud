package com.zifuji.cloud.server.business.module.friend.relation.bo;

import com.zifuji.cloud.base.bean.component.BaseComponentMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendRelationComponentMo extends BaseComponentMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String groupName;

    private String name;

    private String imgUrl;
}
