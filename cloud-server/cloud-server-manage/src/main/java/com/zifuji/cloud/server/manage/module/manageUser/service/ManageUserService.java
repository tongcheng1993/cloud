package com.zifuji.cloud.server.manage.module.manageUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.qo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.vo.*;

import java.util.List;

public interface ManageUserService {

    DrawCaptchaVo drawCaptcha();

    String login(LoginMo loginMo);

    List<ManageMenuVo> getMenu();

    IPage<ManageUserVo> queryPageManageUser(QueryPageManageUserQo queryPageManageUserQo);

    ManageUserVo getManageUserById(Long id);

    ManageUserVo getManageUserByUserName(String userName);

    Boolean addManageUser(AddManageUserMo addManageUserMo);

    Boolean resetManageUser(ResetManageUserMo resetManageUserMo);

    List<ManageRoleVo> queryListManageRole(QueryListManageRoleQo queryListManageRoleQo);

    Boolean bindUserAndRoleDelBefore(BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo);

    IPage<ManageRoleVo> queryPageManageRole(QueryPageManageRoleQo queryPageManageRoleQo);

    ManageRoleVo getManageRoleById(String id);

    ManageRoleVo getManageRoleByRoleCode(String roleCode);

    Boolean addManageRole(AddManageRoleMo addManageRoleMo);

    Boolean resetManageRole(ResetManageRoleMo resetManageRoleMo);

    List<ManagePermissionVo> queryListManagePermission(QueryManagePermissionQo queryManagePermissionQo);

    List<ManageMenuVo> queryListManageMenu(QueryListManageMenuQo queryListManageMenuQo);

    Boolean bindRoleAndPermissionDelBefore(BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo);

    Boolean bindRoleAndMenuDelBefore(BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo);

    IPage<ManagePermissionVo> queryPageManagePermission(QueryManagePermissionQo queryManagePermissionQo);

    ManagePermissionVo addManagePermission(AddManagePermissionMo addManagePermissionMo);

}
