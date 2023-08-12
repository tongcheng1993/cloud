package com.zifuji.cloud.server.base.bean;

import java.io.Serializable;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseControllerPageQo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private long size = 10;

    private long current = 1;

    private List<BaseOrderItem> orders;


}
