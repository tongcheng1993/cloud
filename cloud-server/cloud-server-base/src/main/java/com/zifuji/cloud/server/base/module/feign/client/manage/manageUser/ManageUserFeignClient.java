package com.zifuji.cloud.server.base.module.feign.client.manage.manageUser;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.base.module.feign.client.manage.manageUser.mo.AddManagePermissionMo;
import com.zifuji.cloud.server.base.module.feign.client.manage.manageUser.vo.ManagePermissionVo;

@FeignClient(name = "cloud-server-manage", contextId = "manageUser", path = "/manageUser")
public interface ManageUserFeignClient {

	@PostMapping(value = "/addManagePermissionInner")
	public Result<ManagePermissionVo> addManagePermissionInner(
			@RequestBody @Valid AddManagePermissionMo addManagePermissionMo);
}
