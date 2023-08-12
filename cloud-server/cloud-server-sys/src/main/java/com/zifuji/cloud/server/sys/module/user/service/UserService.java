package com.zifuji.cloud.server.sys.module.user.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.user.bo.WebPermissionComponentMo;
import com.zifuji.cloud.server.sys.module.user.bo.WebRoleComponentMo;
import com.zifuji.cloud.server.sys.module.user.mo.*;
import com.zifuji.cloud.server.sys.module.user.qo.WebMenuPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebPermissionPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebRolePageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebUserPageQo;
import com.zifuji.cloud.server.sys.module.user.vo.*;

public interface UserService {

    List<WebMenuControllerVo> getMenu();

    WebDrawCaptchaControllerVo drawCaptcha();

    String register(RegisterControllerMo registerMo);

    String login(LoginControllerMo loginMo);

    WebUserControllerVo getUserInfo();

    WebPeopleControllerVo getPeopleInfo();

    WebCompanyControllerVo getCompanyInfo();

    WebPeopleControllerVo savePeopleInfo(SavePeopleInfoControllerMo savePeopleInfoMo);

    WebCompanyControllerVo saveCompanyInfo(SaveCompanyInfoControllerMo saveCompanyInfoMo);



    String changePassWord(ChangePassWordControllerMo changePassWordMo);

    String sendBindEmailCaptcha(SendBindEmailCaptchaControllerMo sendBindEmailCaptchaMo);

    Boolean saveBindEmail(SaveBindEmailControllerMo saveBindEmailMo);

    String sendBindPhoneCaptcha(SendBindPhoneCaptchaControllerMo sendBindPhoneCaptchaMo);

    Boolean saveBindPhone(SaveBindPhoneControllerMo saveBindPhoneMo);

    String sendForgetPassWordCaptcha(SendForgetPassWordCaptchaControllerMo sendForgetPassWordCaptchaMo);

    Boolean resetForgetPassWord(ResetForgetPassWordControllerMo resetForgetPassWordMo);
























    IPage<WebUserControllerVo> queryPageUser(WebUserPageQo webUserPageQo);
    String saveUserAndRole(Long userId, String roleCode);






    List<String> getListMenuIdByRoleId(Long roleId);



    String saveRole(SaveWebRoleControllerMo saveWebRoleMo);
    IPage<WebRoleControllerVo> queryPageRole(WebRolePageQo webRolePageQo);
    String saveUserAndRole(Long userId, Long roleId);









    String savePermission(SaveWebPermissionControllerMo saveWebPermissionMo);
    String saveRoleAndPermission(SaveRoleAndPermissionControllerMo saveRoleAndPermissionMo);
    IPage<WebPermissionVo> queryPagePermission(WebPermissionPageQo webPermissionPageQo);
    List<WebPermissionVo> queryListPermission(WebPermissionPageQo webPermissionPageQo);

    List<WebUserControllerVo> queryListUser(WebUserPageQo webUserPageQo);





    String saveMenu(SaveWebMenuControllerMo saveWebMenuMo);

    String saveRoleAndMenu(SaveRoleAndMenuControllerMo saveRoleAndMenuMo);

    Boolean delMenu(Long menuId);

    WebMenuControllerVo getMenuInfoById(Long menuId);

    List<WebMenuControllerVo> queryListMenu(WebMenuPageQo webMenuPageQo);

    IPage<WebMenuControllerVo> queryPageMenu(WebMenuPageQo webMenuPageQo);




    List<WebRoleComponentMo> selectListRole(WebRolePageQo webRolePageQo);

    IPage<WebPermissionComponentMo> selectPagePermission(WebPermissionPageQo webPermissionPageQo);

    List<WebPermissionComponentMo> selectListPermission(WebPermissionPageQo webPermissionPageQo);


}
