package com.zifuji.cloud.server.business.module.score.qo;

import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ScoreRecordPageQo extends BaseControllerPageQo {

    private String name;


}