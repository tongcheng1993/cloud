package com.zifuji.cloud.server.base.module.feign.client.business.webUser;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.base.module.feign.client.business.webUser.qo.QueryWebUserQo;
import com.zifuji.cloud.server.base.module.feign.client.business.webUser.vo.WebUserVo;

@FeignClient(name = "cloud-server-business", contextId = "webUser", path = "/webUser")
public interface WebUserFeignClient {

	@PostMapping(value = "/queryPageWebUser")
	public Result<IPage<WebUserVo>> queryPageWebUser(@RequestBody @Valid QueryWebUserQo queryWebUserQo);

}
