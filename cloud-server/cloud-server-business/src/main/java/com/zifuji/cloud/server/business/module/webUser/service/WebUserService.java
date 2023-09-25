package com.zifuji.cloud.server.business.module.webUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.LoginMo;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.RegisterMo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryListWebMenuQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryPageWebRoleQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryPageWebUserQo;
import com.zifuji.cloud.server.business.module.webUser.controller.vo.*;

import java.util.List;

public interface WebUserService {

    List<GetMenuVo> getMenu();

    DrawCaptchaVo drawCaptcha();

    String login(LoginMo loginMo);

    String register(RegisterMo registerMo);

    GetMyselfInfoVo getMyselfInfo();

    Boolean logout();

    IPage<QueryPageWebUserVo> queryPageWebUser(QueryPageWebUserQo queryPageWebUserQo);

    IPage<QueryPageWebRoleVo> queryPageWebRole(QueryPageWebRoleQo queryPageWebRoleQo);

    List<QueryListWebMenuVo> queryListWebMenu(QueryListWebMenuQo queryListWebMenuQo);
}
