package com.zifuji.cloud.server.business.db.friend.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_friend_apply")
public class FriendApplyEntity extends MyBaseEntity {

	@ApiModelProperty(value = "另一人userId")
	private Long toBy;
	@ApiModelProperty(value = "备注名称")
	private String noteName;
	@ApiModelProperty(value = "分组名称")
	private String groupName;
	@ApiModelProperty(value = "验证信息")
	private String content;

	private String auditStatus;

	private Boolean auditFlag;

	private Boolean showFlag;
	
}
