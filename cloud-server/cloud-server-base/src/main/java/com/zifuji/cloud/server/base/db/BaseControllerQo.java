package com.zifuji.cloud.server.base.db;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
public class BaseControllerQo<T> extends Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long tableId;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;

    private Integer sortNum;

    private Boolean delFlag;

    private Long parentId;


}
