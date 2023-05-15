package com.zifuji.cloud.server.business.module.friend.bo;

import com.zifuji.cloud.base.bean.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendRelationBo extends BaseBo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String groupName;

    private String name;

    private String imgUrl;
}
