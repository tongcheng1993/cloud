package com.zifuji.cloud.base.bean;

import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasePageQo extends BaseMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private long size = 10;

    private long current = 1;

    private List<BaseOrderItem> orders;


}
