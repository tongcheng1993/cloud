package com.zifuji.cloud.server.sys.module.manageUser.qo;

import java.util.List;

import com.zifuji.cloud.base.bean.BasePageQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuPageQo extends BasePageQo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> userName;
	
	private List<String> roleCode;
}
