package com.zifuji.cloud.server.base.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseControllerQo implements Serializable {

	private static final long serialVersionUID = 1L;

	private long size = 10;

	private long current = 1;

	private Long tableIdEq;

	private Long createByEq;

	private LocalDateTime createTimeLe;

	private LocalDateTime createTimeGe;

	private Long updateByEq;

	private LocalDateTime updateTimeLe;

	private LocalDateTime updateTimeGe;

	private Integer sortNumEq;

	private Boolean delFlagEq;

	private Long parentIdEq;

}
