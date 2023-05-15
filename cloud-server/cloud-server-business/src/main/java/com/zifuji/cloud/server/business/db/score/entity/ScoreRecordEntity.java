package com.zifuji.cloud.server.business.db.score.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_score_record")
public class ScoreRecordEntity extends MyBaseEntity {

	
	private Long scoreAccountId;

	private Integer updateNum;
}
