package com.zifuji.cloud.server.business.module.webUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.webUser.service.mo.LoginServiceMo;
import com.zifuji.cloud.server.business.module.webUser.service.vo.DrawCaptchaServiceVo;
import com.zifuji.cloud.server.business.module.webUser.service.vo.WebMenuServiceVo;
import com.zifuji.cloud.server.business.module.webUser.service.vo.WebUserServiceVo;
import com.zifuji.cloud.server.business.module.webUser.service.mo.RegisterServiceMo;

import java.util.List;

public interface UserService {

    List<WebMenuServiceVo> getMenu();

    DrawCaptchaServiceVo drawCaptcha();

    String login(LoginServiceMo loginServiceMo);

    Boolean logout();

    String register(RegisterServiceMo registerServiceMo);

    IPage<WebUserServiceVo> queryPageWebUser();

    IPage<WebMenuServiceVo> queryPageWebMenu();

}
