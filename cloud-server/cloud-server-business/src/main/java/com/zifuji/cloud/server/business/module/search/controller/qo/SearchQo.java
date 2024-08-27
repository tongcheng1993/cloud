package com.zifuji.cloud.server.business.module.search.controller.qo;

import com.zifuji.cloud.server.base.db.BaseQo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchQo extends BaseQo {

	private String keyword;
}
