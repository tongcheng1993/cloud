package com.zifuji.cloud.server.sys.module.user.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.user.bo.WebPermissionBo;
import com.zifuji.cloud.server.sys.module.user.bo.WebRoleBo;
import com.zifuji.cloud.server.sys.module.user.mo.*;
import com.zifuji.cloud.server.sys.module.user.qo.WebMenuPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebPermissionPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebRolePageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebUserPageQo;
import com.zifuji.cloud.server.sys.module.user.vo.*;

public interface UserService {

    List<WebMenuVo> getMenu();

    WebDrawCaptchaVo drawCaptcha();

    String register(RegisterMo registerMo);

    String login(LoginMo loginMo);

    WebUserVo getUserInfo();

    WebPeopleVo getPeopleInfo();

    WebCompanyVo getCompanyInfo();

    WebPeopleVo savePeopleInfo(SavePeopleInfoMo savePeopleInfoMo);

    WebCompanyVo saveCompanyInfo(SaveCompanyInfoMo saveCompanyInfoMo);

    Boolean saveName(SaveNameMo saveNameMo);

    String changePassWord(ChangePassWordMo changePassWordMo);

    String sendBindEmailCaptcha(SendBindEmailCaptchaMo sendBindEmailCaptchaMo);

    Boolean saveBindEmail(SaveBindEmailMo saveBindEmailMo);

    String sendBindPhoneCaptcha(SendBindPhoneCaptchaMo sendBindPhoneCaptchaMo);

    Boolean saveBindPhone(SaveBindPhoneMo saveBindPhoneMo);

    String sendForgetPassWordCaptcha(SendForgetPassWordCaptchaMo sendForgetPassWordCaptchaMo);

    Boolean resetForgetPassWord(ResetForgetPassWordMo resetForgetPassWordMo);
























    IPage<WebUserVo> queryPageUser(WebUserPageQo webUserPageQo);
    String saveUserAndRole(Long userId, String roleCode);






    List<String> getListMenuIdByRoleId(Long roleId);



    String saveRole(SaveWebRoleMo saveWebRoleMo);
    IPage<WebRoleVo> queryPageRole(WebRolePageQo webRolePageQo);
    String saveUserAndRole(Long userId, Long roleId);









    String savePermission(SaveWebPermissionMo saveWebPermissionMo);
    String saveRoleAndPermission(SaveRoleAndPermissionMo saveRoleAndPermissionMo);
    IPage<WebPermissionVo> queryPagePermission(WebPermissionPageQo webPermissionPageQo);
    List<WebPermissionVo> queryListPermission(WebPermissionPageQo webPermissionPageQo);

    List<WebUserVo> queryListUser(WebUserPageQo webUserPageQo);





    String saveMenu(SaveWebMenuMo saveWebMenuMo);
    String saveRoleAndMenu(SaveRoleAndMenuMo saveRoleAndMenuMo);

    Boolean delMenu(Long menuId);

    WebMenuVo getMenuInfoById(Long menuId);

    List<WebMenuVo> queryListMenu(WebMenuPageQo webMenuPageQo);

    IPage<WebMenuVo> queryPageMenu(WebMenuPageQo webMenuPageQo);




    List<WebRoleBo> selectListRole(WebRolePageQo webRolePageQo);
    IPage<WebPermissionBo> selectPagePermission(WebPermissionPageQo webPermissionPageQo);

    List<WebPermissionBo> selectListPermission(WebPermissionPageQo webPermissionPageQo);


}
