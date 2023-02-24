package com.zifuji.cloud.server.sys.module.user.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import com.zifuji.cloud.server.sys.db.user.entity.*;
import com.zifuji.cloud.server.sys.db.user.service.*;
import com.zifuji.cloud.server.sys.module.core.bo.DrawCaptchaBo;
import com.zifuji.cloud.server.sys.module.core.service.CoreService;
import com.zifuji.cloud.server.sys.module.email.service.EmailService;
import com.zifuji.cloud.server.sys.module.user.bo.WebMenuBo;
import com.zifuji.cloud.server.sys.module.user.bo.WebPermissionBo;
import com.zifuji.cloud.server.sys.module.user.bo.WebRoleBo;
import com.zifuji.cloud.server.sys.module.user.bo.WebUserBo;
import com.zifuji.cloud.server.sys.module.user.mo.*;
import com.zifuji.cloud.server.sys.module.user.qo.WebPermissionPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebUserPageQo;
import com.zifuji.cloud.server.sys.module.user.vo.*;
import org.apache.commons.fileupload.FileItem;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.module.user.mapper.UserMapper;
import com.zifuji.cloud.server.sys.module.user.qo.WebMenuPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebRolePageQo;
import com.zifuji.cloud.server.sys.module.user.service.UserService;
import com.zifuji.cloud.starter.web.object.SecurityUtil;
import com.zifuji.cloud.starter.web.util.MyBatisPlusUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private WebUserEntityService webUserEntityService;
    private WebUserRoleEntityService webUserRoleEntityService;
    private WebRoleEntityService webRoleEntityService;
    private WebRoleMenuEntityService webRoleMenuEntityService;
    private WebMenuEntityService webMenuEntityService;
    private WebRolePermissionEntityService webRolePermissionEntityService;
    private WebPermissionEntityService webPermissionEntityService;
    private WebPeopleEntityService webPeopleEntityService;
    private WebCompanyEntityService webCompanyEntityService;
    private StringRedisTemplate stringRedisTemplate;
    private CoreService coreService;
    private EmailService emailService;


    @Override
    public List<WebMenuVo> getMenu() {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        List<String> roleCodeList = new ArrayList<String>();
        if (ObjectUtil.isEmpty(userInfo.getRoleList())) {
            roleCodeList.add(BaseConstant.ROLE_VISIT);
        } else {
            for (int i = 0; i < userInfo.getRoleList().size(); i++) {
                roleCodeList.add(userInfo.getRoleList().get(i).replace("ROLE_", ""));
            }
        }
        WebMenuPageQo webMenuPageQo = new WebMenuPageQo();
        webMenuPageQo.setRoleCodes(roleCodeList);
        List<WebMenuBo> menuBoList = selectListMenu(webMenuPageQo);
        return menuBoList.stream().map(menuBo -> {
            WebMenuVo webMenuVo = new WebMenuVo();
            BeanUtil.copyProperties(menuBo, webMenuVo);
            return webMenuVo;
        }).collect(Collectors.toList());
    }

    @Override
    public WebDrawCaptchaVo drawCaptcha() {
        WebDrawCaptchaVo webDrawCaptchaVo = new WebDrawCaptchaVo();
        DrawCaptchaBo drawCaptchaBo = coreService.drawCaptcha(BaseConstant.BUSINESS_TYPE_WEB, "img");
        BeanUtil.copyProperties(drawCaptchaBo, webDrawCaptchaVo);
        return webDrawCaptchaVo;
    }

    @Override
    public String register(RegisterMo registerMo) {
        coreService.checkCodeAndValue(BaseConstant.BUSINESS_TYPE_WEB, "img", registerMo.getRedisUuid(), registerMo.getValue());

        if (!StrUtil.equals(registerMo.getPassWord(), registerMo.getPassWordSec())) {
            throw new Exception200("密码输入不一致");
        }
        // 通过用户名查询数据库记录
        QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
        webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, registerMo.getUserName());
        WebUserEntity webUserEntity = this.webUserEntityService.getOne(webUserEntityQueryWrapper);
        if (ObjectUtil.isNotNull(webUserEntity)) {
            throw new Exception200("用户名重复");
        }
        // 密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        webUserEntity = new WebUserEntity();
        webUserEntity.setUserName(registerMo.getUserName());
        webUserEntity.setPassWord(bCryptPasswordEncoder.encode(registerMo.getPassWord()));
        webUserEntity.setName(registerMo.getName());
        webUserEntity.setEmail("");
        webUserEntity.setPhone("");
        this.webUserEntityService.save(webUserEntity);

        // 给予权限
        this.saveUserAndRole(webUserEntity.getId(), BaseConstant.ROLE_REGISTER);


        return this.getLoginToken(webUserEntity);
    }

    @Override
    public String login(LoginMo loginMo) {

        coreService.checkCodeAndValue(BaseConstant.BUSINESS_TYPE_WEB, "img", loginMo.getRedisUuid(), loginMo.getValue());
        // 通过用户名查询数据库记录
        QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
        webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, loginMo.getUserName());
        WebUserEntity webUserEntity = webUserEntityService.getOne(webUserEntityQueryWrapper);
        if (ObjectUtil.isNull(webUserEntity)) {
            throw new Exception200("用户名密码错误");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(loginMo.getPassWord(), webUserEntity.getPassWord())) {
            throw new Exception200("用户名密码错误");
        }
        return getLoginToken(webUserEntity);
    }

    private String getLoginToken(WebUserEntity webUserEntity) {
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(webUserEntity, userInfo);
        List<String> roleCodeList = new ArrayList<String>();
        List<String> permissionCodeList = new ArrayList<String>();
        WebRolePageQo webRolePageQo = new WebRolePageQo();
        webRolePageQo.setUserIds(new ArrayList<Long>());
        webRolePageQo.getUserIds().add(userInfo.getId());
        List<WebRoleBo> webRoleBoList = selectListRole(webRolePageQo);
        for (WebRoleBo webRoleBo : webRoleBoList) {
            roleCodeList.add(webRoleBo.getCode());
        }
        WebPermissionPageQo webPermissionPageQo = new WebPermissionPageQo();
        webPermissionPageQo.setUserIds(new ArrayList<Long>());
        webPermissionPageQo.getUserIds().add(userInfo.getId());
        List<WebPermissionBo> webPermissionBoList = selectListPermission(webPermissionPageQo);
        for (WebPermissionBo webPermissionBo : webPermissionBoList) {
            permissionCodeList.add(webPermissionBo.getCodeSys() + ":" + webPermissionBo.getCodeModule() + ":" + webPermissionBo.getCode());
        }
        userInfo.setRoleList(roleCodeList);
        userInfo.setPermissionList(permissionCodeList);
        return getLoginToken(userInfo);
    }


    private String getLoginToken(UserInfo userInfo) {
        // 将用户信息json化，放入redis 获取redis key
        String token = StrUtil.uuid();
<<<<<<< HEAD
        userInfo.setToken(token);
=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), 60 * 30, TimeUnit.SECONDS);
        // 返回key
        return token;
    }


    @Override
    public String sendForgetPassWordCaptcha(SendForgetPassWordCaptchaMo sendForgetPassWordCaptchaMo) {
        // 通过用户名查询数据库记录
        QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
        webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, sendForgetPassWordCaptchaMo.getUserName());
        WebUserEntity webUserEntity = this.webUserEntityService.getOne(webUserEntityQueryWrapper);
        if (ObjectUtil.isNull(webUserEntity)) {
            throw new Exception200("未查询到账户");
        }
        if (StrUtil.isBlank(webUserEntity.getEmail())) {
            throw new Exception200("未查询到绑定邮箱");
        }
        DrawCaptchaBo drawCaptchaBo = coreService.drawCaptcha("forgetPassWord", sendForgetPassWordCaptchaMo.getUserName());

        List<MultipartFile> imgList = new ArrayList<>();
        FileItem fileItem = ZfjFileUtil.createFileItem(new ByteArrayInputStream(drawCaptchaBo.getImgBytes()), "验证码.png");
        imgList.add(new CommonsMultipartFile(fileItem));
        Map<String, Object> map = new HashMap<>();
        map.put("user", webUserEntity.getName());
        emailService.sendEmailTemplateByName(webUserEntity.getEmail(), "forget_pw_captcha", map, imgList, null);

        return drawCaptchaBo.getRedisUuid();
    }

    @Override
    public Boolean resetForgetPassWord(ResetForgetPassWordMo resetForgetPassWordMo) {
        coreService.checkCodeAndValue("forgetPassWord", resetForgetPassWordMo.getUserName(), resetForgetPassWordMo.getRedisUuid(), resetForgetPassWordMo.getValue());
        // 通过用户名查询数据库记录
        QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
        webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, resetForgetPassWordMo.getUserName());
        WebUserEntity webUserEntity = this.webUserEntityService.getOne(webUserEntityQueryWrapper);
        if (ObjectUtil.isNull(webUserEntity)) {
            throw new Exception200("数据错误");
        }
        // 密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        webUserEntity.setPassWord(bCryptPasswordEncoder.encode(resetForgetPassWordMo.getPassWord()));
        this.webUserEntityService.updateById(webUserEntity);
        return true;
    }

    @Override
    public Boolean saveName(SaveNameMo saveNameMo) {
        Long userId = SecurityUtil.getUserDetails().getId();
        WebUserEntity webUserEntity = webUserEntityService.getById(userId);
        webUserEntity.setName(saveNameMo.getName());
        webUserEntityService.updateById(webUserEntity);
        return true;
    }

    @Override
    public String changePassWord(ChangePassWordMo changePassWordMo) {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        WebUserEntity webUserEntity = webUserEntityService.getById(userInfo.getId());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(changePassWordMo.getPassWord(), webUserEntity.getPassWord())) {
            throw new Exception200("旧密码错误");
        }
        webUserEntity.setPassWord(bCryptPasswordEncoder.encode(changePassWordMo.getNewPassWord()));
        this.webUserEntityService.updateById(webUserEntity);
        return webUserEntity.getId() + "";
    }


    @Override
    public WebUserVo getUserInfo() {
        WebUserVo vo = new WebUserVo();
        UserInfo userInfo = SecurityUtil.getUserDetails();
        WebUserEntity webUserEntity = webUserEntityService.getById(userInfo.getId());
        BeanUtil.copyProperties(webUserEntity, vo);
        return vo;
    }


    @Override
    public String sendBindEmailCaptcha(SendBindEmailCaptchaMo sendBindEmailCaptchaMo) {
        DrawCaptchaBo drawCaptchaBo = coreService.drawCaptcha("bindEmail", sendBindEmailCaptchaMo.getEmail());
        List<MultipartFile> imgList = new ArrayList<>();
        FileItem fileItem = ZfjFileUtil.createFileItem(new ByteArrayInputStream(drawCaptchaBo.getImgBytes()), "验证码.png");
        imgList.add(new CommonsMultipartFile(fileItem));
        Map<String, Object> map = new HashMap<>();
        map.put("user", sendBindEmailCaptchaMo.getEmail());
        emailService.sendEmailTemplateByName(sendBindEmailCaptchaMo.getEmail(), "bind_email_captcha", map, imgList, null);
        return drawCaptchaBo.getRedisUuid();
    }

    @Override
    public Boolean saveBindEmail(SaveBindEmailMo saveBindEmailMo) {
        // 校验验证码
        coreService.checkCodeAndValue("bindEmail", saveBindEmailMo.getEmail(), saveBindEmailMo.getRedisUuid(), saveBindEmailMo.getValue());
        WebUserEntity webUserEntity = webUserEntityService.getById(SecurityUtil.getUserDetails().getId());
        // 校验账号和密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(saveBindEmailMo.getPassWord(), webUserEntity.getPassWord())) {
            throw new Exception200("当前密码错误");
        }
        webUserEntity.setEmail(saveBindEmailMo.getEmail());
        webUserEntityService.updateById(webUserEntity);
        return true;
    }

    @Override
    public String sendBindPhoneCaptcha(SendBindPhoneCaptchaMo sendBindPhoneCaptchaMo) {
        DrawCaptchaBo drawCaptchaBo = coreService.drawCaptcha("bindPhone", sendBindPhoneCaptchaMo.getPhone());
<<<<<<< HEAD
        // 发送手机验证码


=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df

        return drawCaptchaBo.getRedisUuid();
    }

    @Override
    public Boolean saveBindPhone(SaveBindPhoneMo saveBindPhoneMo) {
        // 校验验证码
<<<<<<< HEAD
        coreService.checkCodeAndValue("bindPhone", saveBindPhoneMo.getPhone(), saveBindPhoneMo.getRedisUuid(), saveBindPhoneMo.getValue());
=======
//        coreService.checkCodeAndValue("bindPhone", saveBindPhoneMo.getPhone(), saveBindPhoneMo.getRedisUuid(), saveBindPhoneMo.getValue());
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        WebUserEntity webUserEntity = webUserEntityService.getById(SecurityUtil.getUserDetails().getId());
        // 校验账号和密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(saveBindPhoneMo.getPassWord(), webUserEntity.getPassWord())) {
            throw new Exception200("当前密码错误");
        }
        webUserEntity.setPhone(saveBindPhoneMo.getPhone());
        webUserEntityService.updateById(webUserEntity);
        return true;
    }


    @Override
    public WebPeopleVo savePeopleInfo(SavePeopleInfoMo savePeopleInfoMo) {
        // 保存个人信息
        WebPeopleVo vo = new WebPeopleVo();
        WebPeopleEntity webPeopleEntity = new WebPeopleEntity();
        webPeopleEntity.setPeopleName(savePeopleInfoMo.getPeopleName());
        webPeopleEntity.setCardNumber(savePeopleInfoMo.getCardNumber());
        webPeopleEntityService.save(webPeopleEntity);
        // 个人信息与账号信息的绑定
<<<<<<< HEAD

        UserInfo userInfo = SecurityUtil.getUserDetails();
        Long userId =userInfo  .getId();
=======
        Long userId = SecurityUtil.getUserDetails().getId();
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        WebUserEntity webUserEntity = webUserEntityService.getById(userId);
        webUserEntity.setType("1");
        webUserEntity.setPeopleId(webPeopleEntity.getId());
        webUserEntityService.updateById(webUserEntity);
        // 将用户的角色进行修改--增加个人的权限
<<<<<<< HEAD
        this.saveUserAndRole(webUserEntity.getId(), BaseConstant.ROLE_BIND_PEOPLE);
        // 刷新当前登录token对应的权限
        flashTokenUserInfo(  userInfo.getToken(),webUserEntity);
=======

        // 刷新当前登录token对应的权限

>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        BeanUtil.copyProperties(savePeopleInfoMo, vo);
        return vo;
    }

    @Override
    public WebPeopleVo getPeopleInfo() {
        WebPeopleVo vo = new WebPeopleVo();
        Long userId = SecurityUtil.getUserDetails().getId();
        QueryWrapper<WebPeopleEntity> webPeopleEntityQueryWrapper = new QueryWrapper<>();
        webPeopleEntityQueryWrapper.lambda().eq(WebPeopleEntity::getCreateBy, userId);
        WebPeopleEntity webPeopleEntity = webPeopleEntityService.getOne(webPeopleEntityQueryWrapper);
        if (ObjectUtil.isNull(webPeopleEntity)) {
            return vo;
        }
        BeanUtil.copyProperties(webPeopleEntity, vo);
        return vo;
    }

    @Override
    public WebCompanyVo saveCompanyInfo(SaveCompanyInfoMo saveCompanyInfoMo) {
        // 保存单位信息
        WebCompanyVo vo = new WebCompanyVo();
        WebCompanyEntity webCompanyEntity = new WebCompanyEntity();
        webCompanyEntity.setCompanyName(saveCompanyInfoMo.getCompanyName());
        webCompanyEntity.setDeptCode(saveCompanyInfoMo.getDeptCode());
        webCompanyEntityService.save(webCompanyEntity);
        // 将账号信息和单位信息绑定
<<<<<<< HEAD
        UserInfo userInfo = SecurityUtil.getUserDetails();
        Long userId =userInfo  .getId();
=======
        Long userId = SecurityUtil.getUserDetails().getId();
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        WebUserEntity webUserEntity = webUserEntityService.getById(userId);
        webUserEntity.setType("2");
        webUserEntity.setCompanyId(webCompanyEntity.getId());
        webUserEntityService.updateById(webUserEntity);
        // 将用户的角色进行修改--增加单位的权限
<<<<<<< HEAD
        this.saveUserAndRole(webUserEntity.getId(), BaseConstant.ROLE_BIND_DEPT);
        // 刷新当前登录token对应的权限
        flashTokenUserInfo(  userInfo.getToken(),webUserEntity);
=======

        // 刷新当前登录token对应的权限

>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        BeanUtil.copyProperties(saveCompanyInfoMo, vo);
        return vo;
    }

<<<<<<< HEAD
    private void flashTokenUserInfo(String token,WebUserEntity webUserEntity){

        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(webUserEntity, userInfo);
        List<String> roleCodeList = new ArrayList<String>();
        List<String> permissionCodeList = new ArrayList<String>();
        WebRolePageQo webRolePageQo = new WebRolePageQo();
        webRolePageQo.setUserIds(new ArrayList<Long>());
        webRolePageQo.getUserIds().add(userInfo.getId());
        List<WebRoleBo> webRoleBoList = selectListRole(webRolePageQo);
        for (WebRoleBo webRoleBo : webRoleBoList) {
            roleCodeList.add(webRoleBo.getCode());
        }
        WebPermissionPageQo webPermissionPageQo = new WebPermissionPageQo();
        webPermissionPageQo.setUserIds(new ArrayList<Long>());
        webPermissionPageQo.getUserIds().add(userInfo.getId());
        List<WebPermissionBo> webPermissionBoList = selectListPermission(webPermissionPageQo);
        for (WebPermissionBo webPermissionBo : webPermissionBoList) {
            permissionCodeList.add(webPermissionBo.getCodeSys() + ":" + webPermissionBo.getCodeModule() + ":" + webPermissionBo.getCode());
        }
        userInfo.setRoleList(roleCodeList);
        userInfo.setPermissionList(permissionCodeList);

        userInfo.setToken(token);
        log.info(token);
        String bo =stringRedisTemplate.opsForValue().get(token);
        log.info(bo);
        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), 60 * 30, TimeUnit.SECONDS);

    }

=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    @Override
    public WebCompanyVo getCompanyInfo() {
        WebCompanyVo vo = new WebCompanyVo();
        Long userId = SecurityUtil.getUserDetails().getId();
        QueryWrapper<WebCompanyEntity> webCompanyEntityQueryWrapper = new QueryWrapper<>();
        webCompanyEntityQueryWrapper.lambda().eq(WebCompanyEntity::getCreateBy, userId);
        WebCompanyEntity webCompanyEntity = webCompanyEntityService.getOne(webCompanyEntityQueryWrapper);
        if (ObjectUtil.isNull(webCompanyEntity)) {
            return vo;
        }
        BeanUtil.copyProperties(webCompanyEntity, vo);
        return vo;
    }


    @Override
    public IPage<WebUserVo> queryPageUser(WebUserPageQo webUserPageQo) {

        IPage<WebUserBo> page = selectPageUser(webUserPageQo);

        return page.convert(webUserBo -> {
            WebUserVo webUserVo = new WebUserVo();
            BeanUtil.copyProperties(webUserBo, webUserVo);
            return webUserVo;
        });
    }

    @Override
    public List<WebUserVo> queryListUser(WebUserPageQo webUserPageQo) {
        List<WebUserBo> list = selectListUser(webUserPageQo);
        return list.stream().map(webUserBo -> {
            WebUserVo webUserVo = new WebUserVo();
            BeanUtil.copyProperties(webUserBo, webUserVo);
            return webUserVo;
        }).collect(Collectors.toList());
    }

    @Override
    public String saveRole(SaveWebRoleMo saveWebRoleMo) {
        QueryWrapper<WebRoleEntity> queryWrapper = new QueryWrapper<WebRoleEntity>();
        queryWrapper.lambda().eq(WebRoleEntity::getCode, saveWebRoleMo.getCode());
        WebRoleEntity webRoleEntity = webRoleEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNotNull(webRoleEntity)) {
            throw new Exception200("");
        }
        webRoleEntity = new WebRoleEntity();
        BeanUtil.copyProperties(saveWebRoleMo, webRoleEntity);
        webRoleEntityService.save(webRoleEntity);
        return webRoleEntity.getId() + "";
    }


    @Override
    public IPage<WebRoleVo> queryPageRole(WebRolePageQo webRolePageQo) {
        IPage<WebRoleBo> page = selectPageRole(webRolePageQo);
        return page.convert(webRoleBo -> {
            WebRoleVo webRoleVo = new WebRoleVo();
            BeanUtil.copyProperties(webRoleBo, webRoleVo);
            return webRoleVo;
        });
    }

    @Override
    public String saveMenu(SaveWebMenuMo saveWebMenuMo) {
<<<<<<< HEAD
        if (ObjectUtil.isNotNull(saveWebMenuMo.getId()) && saveWebMenuMo.getId() > 0) {
            WebMenuEntity webMenuEntity = webMenuEntityService.getById(saveWebMenuMo.getId());


            return webMenuEntity.getId() + "";
        }else{
            QueryWrapper<WebMenuEntity> queryWrapper = new QueryWrapper<WebMenuEntity>();
            queryWrapper.lambda()
                    .eq(WebMenuEntity::getParentId,saveWebMenuMo.getParentId())
                    .eq(WebMenuEntity::getPath, saveWebMenuMo.getPath());
            WebMenuEntity webMenuEntity = webMenuEntityService.getOne(queryWrapper);
            if (ObjectUtil.isNotNull(webMenuEntity)) {
                throw new Exception200("存在相同的页面地址，请确认数据");
            }
            webMenuEntity = new WebMenuEntity();
            BeanUtil.copyProperties(saveWebMenuMo, webMenuEntity);
            webMenuEntityService.save(webMenuEntity);
            return webMenuEntity.getId() + "";
        }


=======
        QueryWrapper<WebMenuEntity> queryWrapper = new QueryWrapper<WebMenuEntity>();
        queryWrapper.lambda().eq(WebMenuEntity::getPath, saveWebMenuMo.getPath());
        WebMenuEntity webMenuEntity = webMenuEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNotNull(webMenuEntity)) {
            throw new Exception200("");
        }
        webMenuEntity = new WebMenuEntity();
        BeanUtil.copyProperties(saveWebMenuMo, webMenuEntity);
        webMenuEntityService.save(webMenuEntity);
        return webMenuEntity.getId() + "";
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    }

    @Override
    public Boolean delMenu(Long menuId) {

        return webMenuEntityService.removeById(menuId);
    }


    @Override
    public List<WebMenuVo> queryListMenu(WebMenuPageQo webMenuPageQo) {
        List<WebMenuBo> list = selectListMenu(webMenuPageQo);
        return list.stream().map(webMenuBo -> {
            WebMenuVo webMenuVo = new WebMenuVo();
            BeanUtil.copyProperties(webMenuBo, webMenuVo);
            return webMenuVo;
        }).collect(Collectors.toList());
    }

    @Override
    public IPage<WebMenuVo> queryPageMenu(WebMenuPageQo webMenuPageQo) {
        IPage<WebMenuBo> page = selectPageMenu(webMenuPageQo);
        return page.convert(webMenuBo -> {
            WebMenuVo webMenuVo = new WebMenuVo();
            BeanUtil.copyProperties(webMenuBo, webMenuVo);
            return webMenuVo;
        });
    }

    @Override
    public String savePermission(SaveWebPermissionMo saveWebPermissionMo) {
        QueryWrapper<WebPermissionEntity> queryWrapper = new QueryWrapper<WebPermissionEntity>();
<<<<<<< HEAD
        queryWrapper.lambda()
                .eq(WebPermissionEntity::getCodeSys,saveWebPermissionMo.getCodeSys())
                .eq(WebPermissionEntity::getCodeModule,saveWebPermissionMo.getCodeModule())
                .eq(WebPermissionEntity::getCode, saveWebPermissionMo.getCode());
=======
        queryWrapper.lambda().eq(WebPermissionEntity::getCode, saveWebPermissionMo.getCode());
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        WebPermissionEntity webPermissionEntity = webPermissionEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNotNull(webPermissionEntity)) {
            throw new Exception200("");
        }
        webPermissionEntity = new WebPermissionEntity();
        BeanUtil.copyProperties(saveWebPermissionMo, webPermissionEntity);
        webPermissionEntityService.save(webPermissionEntity);
        return webPermissionEntity.getId() + "";
    }

    @Override
    public List<String> getListMenuIdByRoleId(Long roleId) {
        List<String> list = new ArrayList<String>();
        QueryWrapper<WebRoleMenuEntity> queryWrapper = new QueryWrapper<WebRoleMenuEntity>();
        queryWrapper.lambda().eq(WebRoleMenuEntity::getRoleId, roleId);
        List<WebRoleMenuEntity> webRoleMenuEntityList = webRoleMenuEntityService.list(queryWrapper);
        for (WebRoleMenuEntity webRoleMenuEntity : webRoleMenuEntityList) {
            list.add("" + webRoleMenuEntity.getId());
        }
        return list;
    }

    @Override
    public String saveRoleAndMenu(SaveRoleAndMenuMo saveRoleAndMenuMo) {
        QueryWrapper<WebRoleMenuEntity> queryWrapper = new QueryWrapper<WebRoleMenuEntity>();
        queryWrapper.lambda().eq(WebRoleMenuEntity::getRoleId, saveRoleAndMenuMo.getRoleId());
        webRoleMenuEntityService.remove(queryWrapper);

        List<WebRoleMenuEntity> list = new ArrayList<WebRoleMenuEntity>();
        if (ObjectUtil.isNotEmpty(saveRoleAndMenuMo.getMenuIdList())) {
            for (Long menuId : saveRoleAndMenuMo.getMenuIdList()) {
                WebRoleMenuEntity webRoleMenuEntity = new WebRoleMenuEntity();
                webRoleMenuEntity.setRoleId(saveRoleAndMenuMo.getRoleId());
                webRoleMenuEntity.setMenuId(menuId);
                list.add(webRoleMenuEntity);
            }
        }

        webRoleMenuEntityService.saveBatch(list);
        return saveRoleAndMenuMo.getRoleId() + "";
    }

    @Override
    public WebMenuVo getMenuInfoById(Long menuId) {
        WebMenuVo vo = new WebMenuVo();
        WebMenuEntity webMenuEntity = webMenuEntityService.getById(menuId);
        BeanUtil.copyProperties(webMenuEntity, vo);
        return vo;
    }

    @Override
    public String saveRoleAndPermission(SaveRoleAndPermissionMo saveRoleAndPermissionMo) {
<<<<<<< HEAD
        QueryWrapper<WebRolePermissionEntity> queryWrapper = new QueryWrapper<WebRolePermissionEntity>();
        queryWrapper.lambda().eq(WebRolePermissionEntity::getRoleId, saveRoleAndPermissionMo.getRoleId());
        webRolePermissionEntityService.remove(queryWrapper);
        if (ObjectUtil.isNotEmpty(saveRoleAndPermissionMo.getPermissionIdList())) {
            for (Long permissionId : saveRoleAndPermissionMo.getPermissionIdList()) {
                WebRolePermissionEntity webRolePermissionEntity = new WebRolePermissionEntity();
                webRolePermissionEntity.setRoleId(saveRoleAndPermissionMo.getRoleId());
                webRolePermissionEntity.setPermissionId(permissionId);
                webRolePermissionEntityService.save(webRolePermissionEntity);
            }
        }
        return saveRoleAndPermissionMo.getRoleId() + "";

=======
        // TODO Auto-generated method stub
        return null;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    }

    @Override
    public String saveUserAndRole(Long userId, String roleCode) {
        QueryWrapper<WebRoleEntity> roleQueryWrapper = new QueryWrapper<WebRoleEntity>();
        roleQueryWrapper.lambda().eq(WebRoleEntity::getCode, roleCode);
        WebRoleEntity webRoleEntity = webRoleEntityService.getOne(roleQueryWrapper);
        if (ObjectUtil.isNull(webRoleEntity)) {
            throw new Exception200("");
        }
        return saveUserAndRole(userId, webRoleEntity.getId());
    }

    @Override
    public String saveUserAndRole(Long userId, Long roleId) {
        QueryWrapper<WebUserRoleEntity> queryWrapper = new QueryWrapper<WebUserRoleEntity>();
        queryWrapper.lambda()
                .eq(WebUserRoleEntity::getUserId, userId)
                .eq(WebUserRoleEntity::getRoleId, roleId);
        WebUserRoleEntity webUserRoleEntity = webUserRoleEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(webUserRoleEntity)) {
            webUserRoleEntity = new WebUserRoleEntity();
            webUserRoleEntity.setUserId(userId);
            webUserRoleEntity.setRoleId(roleId);
            webUserRoleEntityService.save(webUserRoleEntity);
            return webUserRoleEntity.getId() + "";
        } else {
            return webUserRoleEntity.getId() + "";
        }

    }

    private IPage<WebUserBo> selectPageUser(WebUserPageQo webUserPageQo) {
        Page<WebUserBo> page = new Page<WebUserBo>(webUserPageQo.getCurrent(), webUserPageQo.getSize());
        QueryWrapper<WebUserBo> queryWrapper = new QueryWrapper<WebUserBo>();

        MyBatisPlusUtil.orderWrapper(queryWrapper, webUserPageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectPageUser(page, queryWrapper);
    }

    private List<WebUserBo> selectListUser(WebUserPageQo webUserPageQo) {
        QueryWrapper<WebUserBo> queryWrapper = new QueryWrapper<WebUserBo>();

        MyBatisPlusUtil.orderWrapper(queryWrapper, webUserPageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectListUser(queryWrapper);
    }

    public List<WebRoleBo> selectListRole(WebRolePageQo webRolePageQo) {
        QueryWrapper<WebRoleBo> queryWrapper = new QueryWrapper<WebRoleBo>();
        if (ObjectUtil.isNotEmpty(webRolePageQo.getUserIds())) {
            queryWrapper.in("zwu.id", webRolePageQo.getUserIds());
        }

        MyBatisPlusUtil.orderWrapper(queryWrapper, webRolePageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectListRole(queryWrapper);
    }

    @Override
    public IPage<WebPermissionVo> queryPagePermission(WebPermissionPageQo webPermissionPageQo) {
<<<<<<< HEAD
        IPage<WebPermissionBo> page = selectPagePermission(webPermissionPageQo);
        return page.convert(webPermissionBo -> {
            WebPermissionVo webPermissionVo = new WebPermissionVo();
            BeanUtil.copyProperties(webPermissionBo, webPermissionVo);
            return webPermissionVo;
        });
    }

    @Override
    public List<WebPermissionVo> queryListPermission(WebPermissionPageQo webPermissionPageQo) {
        List<WebPermissionBo> list = selectListPermission(webPermissionPageQo);
        return list.stream().map(webPermissionBo -> {
            WebPermissionVo webPermissionVo = new WebPermissionVo();
            BeanUtil.copyProperties(webPermissionBo, webPermissionVo);
            return webPermissionVo;
        }).collect(Collectors.toList());
=======
        return null;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    }

    private IPage<WebRoleBo> selectPageRole(WebRolePageQo webRolePageQo) {
        Page<WebRoleBo> page = new Page<WebRoleBo>(webRolePageQo.getCurrent(), webRolePageQo.getSize());
        QueryWrapper<WebRoleBo> queryWrapper = new QueryWrapper<WebRoleBo>();
        MyBatisPlusUtil.orderWrapper(queryWrapper, webRolePageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectPageRole(page, queryWrapper);
    }

    private List<WebMenuBo> selectListMenu(WebMenuPageQo webMenuPageQo) {
        QueryWrapper<WebMenuBo> queryWrapper = new QueryWrapper<WebMenuBo>();
        if (ObjectUtil.isNotEmpty(webMenuPageQo.getRoleIds())) {
            queryWrapper.in("zwr.id", webMenuPageQo.getRoleIds());
        }
        if (ObjectUtil.isNotEmpty(webMenuPageQo.getRoleCodes())) {
            queryWrapper.in("zwr.code", webMenuPageQo.getRoleCodes());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, webMenuPageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectListMenu(queryWrapper);
    }

    private IPage<WebMenuBo> selectPageMenu(WebMenuPageQo webMenuPageQo) {
        Page<WebMenuBo> page = new Page<WebMenuBo>(webMenuPageQo.getCurrent(), webMenuPageQo.getSize());
        QueryWrapper<WebMenuBo> queryWrapper = new QueryWrapper<WebMenuBo>();
        if (ObjectUtil.isNotEmpty(webMenuPageQo.getRoleCodes())) {
            queryWrapper.in("zwr.code", webMenuPageQo.getRoleCodes());
        }
        queryWrapper.groupBy("id");
        MyBatisPlusUtil.orderWrapper(queryWrapper, webMenuPageQo.getOrders());

        return userMapper.selectPageMenu(page, queryWrapper);
    }

    public List<WebPermissionBo> selectListPermission(WebPermissionPageQo webPermissionPageQo) {
        QueryWrapper<WebPermissionBo> queryWrapper = new QueryWrapper<WebPermissionBo>();
        if (ObjectUtil.isNotEmpty(webPermissionPageQo.getUserIds())) {
            queryWrapper.in("zwu.id", webPermissionPageQo.getUserIds());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, webPermissionPageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectListPermission(queryWrapper);
    }

<<<<<<< HEAD
    public IPage<WebPermissionBo> selectPagePermission(WebPermissionPageQo webPermissionPageQo) {
=======
    private IPage<WebPermissionBo> selectPagePermission(WebPermissionPageQo webPermissionPageQo) {
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        Page<WebPermissionBo> page = new Page<WebPermissionBo>(webPermissionPageQo.getCurrent(), webPermissionPageQo.getSize());
        QueryWrapper<WebPermissionBo> queryWrapper = new QueryWrapper<WebPermissionBo>();
        if (ObjectUtil.isNotEmpty(webPermissionPageQo.getUserIds())) {
            queryWrapper.in("zwu.id", webPermissionPageQo.getUserIds());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, webPermissionPageQo.getOrders());
        queryWrapper.groupBy("id");
        return userMapper.selectPagePermission(page, queryWrapper);
    }


}