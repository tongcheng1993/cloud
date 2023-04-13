package com.zifuji.cloud.server.sys.module.manageUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.manageUser.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageMenuPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManagePermissionPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageRolePageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageUserPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.*;


import java.util.List;

public interface ManageUserService {

    DrawCaptchaVo drawCaptcha();

    String login(LoginMo loginMo);

    List<ManageMenuVo> getMenu();

    IPage<ManageUserVo> queryPageUser(ManageUserPageQo manageUserPageQo);

    List<ManageUserVo> queryListUser(ManageUserPageQo manageUserPageQo);

    ManageUserVo saveUser(ManageUserMo manageUserMo);

    ManageUserVo resetPassWord(ResetPassWordMo resetPassWordMo);

    IPage<ManageRoleVo> queryPageRole(ManageRolePageQo manageRolePageQo);

    String saveRole(ManageRoleMo manageRoleMo);

    List<ManageRoleVo> queryListRole(ManageRolePageQo manageRolePageQo);

    List<ManagePermissionVo> queryListPermission(ManagePermissionPageQo managePermissionPageQo);

    IPage<ManagePermissionVo> queryPagePermission(ManagePermissionPageQo managePermissionPageQo);

    String savePermission(ManagePermissionMo managePermissionMo);

    List<ManageMenuVo> queryListMenu(ManageMenuPageQo manageMenuPageQo);

    String saveMenu(ManageMenuMo manageMenuMo);

    String saveUserAndRole(Long userId, String roleCode);

    String saveUserAndRole(Long userId, Long roleId);

    String saveUserRoleRelation(ManageUserRoleRelationMo manageUserRoleRelationMo);

    String saveRolePermissionRelation(ManageRolePermissionRelationMo manageRolePermissionRelationMo);

    String saveRoleMenuRelation(ManageRoleMenuRelationMo manageRoleMenuRelationMo);

    String saveRoleMenuRelation(Long roleId, Long menuId);

    String saveRoleMenuRelation(String roleCode, Long menuId);

    Boolean initManage();
}
