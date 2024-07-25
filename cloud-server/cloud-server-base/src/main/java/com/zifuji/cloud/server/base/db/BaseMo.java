package com.zifuji.cloud.server.base.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseMo implements Serializable {


    private static final long serialVersionUID = 1L;
    @NotNull(message = "tableId 不能为空")
    private Long tableId;

}
