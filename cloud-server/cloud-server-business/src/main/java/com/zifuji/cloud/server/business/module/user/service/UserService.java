package com.zifuji.cloud.server.business.module.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.user.service.mo.LoginMo;
import com.zifuji.cloud.server.business.module.user.service.bo.DrawCaptchaBo;
import com.zifuji.cloud.server.business.module.user.service.bo.WebMenuBo;
import com.zifuji.cloud.server.business.module.user.service.bo.WebUserBo;

import java.util.List;

public interface UserService {

    List<WebMenuBo> getMenu();

    DrawCaptchaBo drawCaptcha();

    String login(LoginMo loginMo);

    Boolean logout();

    IPage<WebUserBo> queryPageWebUser();

    IPage<WebMenuBo> queryPageWebMenu();

}
