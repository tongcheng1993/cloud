package com.zifuji.cloud.server.sys.module.manageUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.zifuji.cloud.server.sys.module.manageUser.controller.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.controller.qo.*;
import com.zifuji.cloud.server.sys.module.manageUser.controller.vo.*;


import java.util.List;

public interface ManageUserService {

    DrawCaptchaVo drawCaptcha();

    String login(LoginMo loginMo);

    List<GetMenuVo> getMenu();

    IPage<QueryPageManageUserVo> queryPageManageUser(QueryPageManageUserQo queryPageManageUserQo);

    GetManageUserVo getManageUserById(String id);

    GetManageUserVo getManageUserByUserName(String userName);

    Boolean addManageUser(AddManageUserMo addManageUserMo);

    Boolean resetManageUser(ResetManageUserMo resetManageUserMo);

    List<QueryListManageRoleVo> queryListManageRole(QueryListManageRoleQo queryListManageRoleQo);

    Boolean bindUserAndRoleDelBefore(BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo);

    IPage<QueryPageManageRoleVo> queryPageManageRole(QueryPageManageRoleQo queryPageManageRoleQo);

    GetManageRoleVo getManageRoleById(String id);

    GetManageRoleVo getManageRoleByRoleCode(String roleCode);

    Boolean addManageRole(AddManageRoleMo addManageRoleMo);

    Boolean resetManageRole(ResetManageRoleMo resetManageRoleMo);

    List<QueryListManagePermissionVo> queryListManagePermission(QueryListManagePermissionQo queryListManagePermissionQo);

    List<QueryListManageMenuVo> queryListManageMenu(QueryListManageMenuQo queryListManageMenuQo);

    Boolean bindRoleAndPermissionDelBefore(BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo);

    Boolean bindRoleAndMenuDelBefore(BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo);

    IPage<QueryPageManagePermissionVo> queryPageManagePermission(QueryPageManagePermissionQo queryPageManagePermissionQo);

}
