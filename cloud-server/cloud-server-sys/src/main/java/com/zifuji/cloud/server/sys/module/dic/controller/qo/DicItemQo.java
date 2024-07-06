package com.zifuji.cloud.server.sys.module.dic.controller.qo;

import com.zifuji.cloud.server.base.db.BaseControllerQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class DicItemQo<T> extends BaseControllerQo<T> {

    private Long dicId;



}