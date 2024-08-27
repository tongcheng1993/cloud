package com.zifuji.cloud.server.business.module.webUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.AddWebRoleMo;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.LoginMo;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.RegisterMo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebMenuQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebRoleQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebUserQo;
import com.zifuji.cloud.server.business.module.webUser.controller.vo.*;

import java.util.List;

public interface WebUserService {

	List<WebMenuVo> getMenu();

	WebUserVo getMyselfInfo();

	Boolean logout();

	String register(RegisterMo registerMo);

	String login(LoginMo loginMo);

	DrawCaptchaVo drawCaptcha();

	IPage<WebUserVo> queryPageWebUser(QueryWebUserQo queryWebUserQo);

	WebRoleVo addWebRole(AddWebRoleMo addWebRoleMo);

	IPage<WebRoleVo> queryPageWebRole(QueryWebRoleQo queryWebRoleQo);

	List<WebMenuVo> queryListWebMenu(QueryWebMenuQo queryWebMenuQo);
}
