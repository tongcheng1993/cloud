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

    DrawCaptchaControllerVo drawCaptcha();

    String login(LoginControllerMo loginMo);

    List<ManageMenuControllerVo> getMenu();

    IPage<ManageUserControllerVo> queryPageUser(ManageUserPageQo manageUserPageQo);

    List<ManageUserControllerVo> queryListUser(ManageUserPageQo manageUserPageQo);

    ManageUserControllerVo saveUser(ManageUserControllerMo manageUserMo);

    ManageUserControllerVo resetPassWord(ResetPassWordControllerMo resetPassWordMo);

    IPage<ManageRoleControllerVo> queryPageRole(ManageRolePageQo manageRolePageQo);

    String saveRole(ManageRoleControllerMo manageRoleMo);

    List<ManageRoleControllerVo> queryListRole(ManageRolePageQo manageRolePageQo);

    List<ManagePermissionControllerVo> queryListPermission(ManagePermissionPageQo managePermissionPageQo);

    IPage<ManagePermissionControllerVo> queryPagePermission(ManagePermissionPageQo managePermissionPageQo);

    String savePermission(ManagePermissionControllerMo managePermissionMo);

    List<ManageMenuControllerVo> queryListMenu(ManageMenuPageQo manageMenuPageQo);

    String saveMenu(ManageMenuControllerMo manageMenuMo);

    String saveUserAndRole(Long userId, String roleCode);

    String saveUserAndRole(Long userId, Long roleId);

    String saveUserRoleRelation(ManageUserRoleRelationControllerMo manageUserRoleRelationMo);

    String saveRolePermissionRelation(ManageRolePermissionRelationControllerMo manageRolePermissionRelationMo);

    String saveRoleMenuRelation(ManageRoleMenuRelationControllerMo manageRoleMenuRelationMo);

    String saveRoleMenuRelation(Long roleId, Long menuId);

    String saveRoleMenuRelation(String roleCode, Long menuId);

    Boolean initManage();
}
