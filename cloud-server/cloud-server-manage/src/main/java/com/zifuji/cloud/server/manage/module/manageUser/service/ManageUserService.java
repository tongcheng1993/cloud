package com.zifuji.cloud.server.manage.module.manageUser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.qo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.vo.*;

import java.util.List;

public interface ManageUserService {
	/**
	 * 
	 * @param manageUserLogoutMo
	 * @return
	 */
	Boolean manageUserLogout(ManageUserLogoutMo manageUserLogoutMo);

	/**
	 * 
	 * @param bindUserAndRoleDelBeforeMo
	 * @return
	 */

	Boolean bindUserAndRoleDelBefore(BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo);

	/**
	 * 
	 * @param bindRoleAndPermissionDelBeforeMo
	 * @return
	 */
	Boolean bindRoleAndPermissionDelBefore(BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo);

	/**
	 * 
	 * @param bindRoleAndMenuDelBeforeMo
	 * @return
	 */
	Boolean bindRoleAndMenuDelBefore(BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo);

	/**
	 * 
	 * @param resetPassWordMo
	 * @return
	 */
	Boolean resetPassWord(ResetPassWordMo resetPassWordMo);

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	DrawCaptchaVo drawCaptcha();

	/**
	 * 
	 * @param loginMo
	 * @return
	 */
	String login(LoginMo loginMo);

	/**
	 * 
	 * @return
	 */
	Boolean logout();

	/**
	 * 
	 * @return
	 */
	List<ManageMenuVo> getMenu();

	/**
	 * 
	 * @param addManageUserMo
	 * @return
	 */
	ManageUserVo addManageUser(AddManageUserMo addManageUserMo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean delManageUser(Long id);

	/**
	 * 
	 * @param updateManageUserMo
	 * @return
	 */
	ManageUserVo updateManageUser(UpdateManageUserMo updateManageUserMo);

	/**
	 * 
	 * @param queryPageManageUserQo
	 * @return
	 */
	List<ManageUserVo> queryListManageUser(QueryPageManageUserQo queryPageManageUserQo);

	/**
	 * 
	 * @param queryPageManageUserQo
	 * @return
	 */
	IPage<ManageUserVo> queryPageManageUser(QueryPageManageUserQo queryPageManageUserQo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ManageUserVo getManageUserById(Long id);

	/**
	 * 
	 * @param addManageRoleMo
	 * @return
	 */
	ManageRoleVo addManageRole(AddManageRoleMo addManageRoleMo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean delManageRole(Long id);

	/**
	 * 
	 * @param manageRoleMo
	 * @return
	 */
	ManageRoleVo updateManageRole(UpdateManageRoleMo manageRoleMo);

	/**
	 * 
	 * @param queryPageManageRoleQo
	 * @return
	 */
	List<ManageRoleVo> queryListManageRole(QueryPageManageRoleQo queryPageManageRoleQo);

	/**
	 * 
	 * @param queryPageManageRoleQo
	 * @return
	 */
	IPage<ManageRoleVo> queryPageManageRole(QueryPageManageRoleQo queryPageManageRoleQo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ManageRoleVo getManageRoleById(Long id);

	/**
	 * 
	 * @param addManagePermissionMo
	 * @return
	 */
	ManagePermissionVo addManagePermission(AddManagePermissionMo addManagePermissionMo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean delManagePermission(Long id);

	/**
	 * 
	 * @param updateManagePermissionMo
	 * @return
	 */
	ManagePermissionVo updateManagePermission(UpdateManagePermissionMo updateManagePermissionMo);

	/**
	 * 
	 * @param queryPageManagePermissionQo
	 * @return
	 */
	List<ManagePermissionVo> queryListManagePermission(QueryPageManagePermissionQo queryPageManagePermissionQo);

	/**
	 * 
	 * @param queryPageManagePermissionQo
	 * @return
	 */
	IPage<ManagePermissionVo> queryPageManagePermission(QueryPageManagePermissionQo queryPageManagePermissionQo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ManagePermissionVo getManagePermissionById(Long id);

	/**
	 * 
	 * @param addManageMenuMo
	 * @return
	 */
	ManageMenuVo addManageMenu(AddManageMenuMo addManageMenuMo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean delManageMenu(Long id);

	/**
	 * 
	 * @param updateManageMenuMo
	 * @return
	 */
	ManageMenuVo updateManageMenu(UpdateManageMenuMo updateManageMenuMo);

	/**
	 * 
	 * @param queryPageManageMenuQo
	 * @return
	 */
	List<ManageMenuVo> queryListManageMenu(QueryPageManageMenuQo queryPageManageMenuQo);

	/**
	 * 
	 * @param queryPageManageMenuQo
	 * @return
	 */
	IPage<ManageMenuVo> queryPageManageMenu(QueryPageManageMenuQo queryPageManageMenuQo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ManageMenuVo getManageMenuById(Long id);

}
