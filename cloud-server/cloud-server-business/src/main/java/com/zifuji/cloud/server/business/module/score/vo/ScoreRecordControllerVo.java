package com.zifuji.cloud.server.business.module.score.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ScoreRecordControllerVo extends BaseControllerVo {

    private Integer updateNum;
}