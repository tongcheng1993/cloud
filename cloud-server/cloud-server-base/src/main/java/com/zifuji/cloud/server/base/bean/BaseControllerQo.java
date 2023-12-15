package com.zifuji.cloud.server.base.bean;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseControllerQo<T>  extends Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long createBy;



}
