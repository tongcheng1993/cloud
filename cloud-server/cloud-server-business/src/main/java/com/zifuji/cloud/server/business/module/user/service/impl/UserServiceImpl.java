package com.zifuji.cloud.server.business.module.user.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.base.util.RedisUtil;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import com.zifuji.cloud.server.business.db.user.entity.*;
import com.zifuji.cloud.server.business.db.user.service.*;
import com.zifuji.cloud.server.business.module.user.mapper.UserMapper;
import com.zifuji.cloud.server.business.module.user.service.UserService;
import com.zifuji.cloud.server.business.module.user.service.bo.DrawCaptchaBo;
import com.zifuji.cloud.server.business.module.user.service.bo.WebMenuBo;
import com.zifuji.cloud.server.business.module.user.service.bo.WebRoleBo;
import com.zifuji.cloud.server.business.module.user.service.bo.WebUserBo;
import com.zifuji.cloud.server.business.module.user.service.mo.LoginMo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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


    @Override
    public List<WebMenuBo> getMenu() {
        List<WebMenuBo> webMenuBoList = new ArrayList<>();
        UserInfo userInfo = SecurityUtil.getUserDetails();
        if (ObjectUtil.isNotEmpty(userInfo.getRoleCodeList())) {
            QueryWrapper<WebRoleEntity> webRoleEntityQueryWrapper = new QueryWrapper<>();
            webRoleEntityQueryWrapper.lambda().in(WebRoleEntity::getCode, userInfo.getRoleCodeList());
            List<WebRoleEntity> webRoleEntityList = webRoleEntityService.list(webRoleEntityQueryWrapper);
            if (ObjectUtil.isNotEmpty(webRoleEntityList)) {
                QueryWrapper<WebRoleMenuEntity> webRoleMenuEntityQueryWrapper = new QueryWrapper<>();
                List<Long> roleIdList = webRoleEntityList.stream().map(WebRoleEntity::getId).collect(Collectors.toList());
                webRoleMenuEntityQueryWrapper.lambda().in(WebRoleMenuEntity::getRoleId, roleIdList);
                List<WebRoleMenuEntity> webRoleMenuEntityList = webRoleMenuEntityService.list(webRoleMenuEntityQueryWrapper);
                if (ObjectUtil.isNotEmpty(webRoleMenuEntityList)) {
                    QueryWrapper<WebMenuEntity> webMenuEntityQueryWrapper = new QueryWrapper<>();
                    List<Long> menuIdList = webRoleMenuEntityList.stream().map(WebRoleMenuEntity::getMenuId).collect(Collectors.toList());
                    webMenuEntityQueryWrapper.lambda()
                            .in(WebMenuEntity::getId, menuIdList)
                            .orderByAsc(WebMenuEntity::getSortNum)
                            .orderByDesc(WebMenuEntity::getCreateTime);
                    List<WebMenuEntity> webMenuEntityList = webMenuEntityService.list(webMenuEntityQueryWrapper);
                    if (ObjectUtil.isNotEmpty(webMenuEntityList)) {
                        webMenuBoList = webMenuEntityList.stream().map(webMenuEntity -> {
                            WebMenuBo bo = new WebMenuBo();
                            BeanUtil.copyProperties(webMenuEntity, bo);
                            return bo;
                        }).collect(Collectors.toList());
                    }
                }
            }

        }


        return webMenuBoList;
    }

    @Override
    public DrawCaptchaBo drawCaptcha() {
        DrawCaptchaBo drawCaptchaBo = new DrawCaptchaBo();
        String redisUuid = StrUtil.uuid();
        drawCaptchaBo.setRedisUuid(redisUuid);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        RandomGenerator randomGenerator = new RandomGenerator(BaseConstant.STRING_C, 4);
        lineCaptcha.setGenerator(randomGenerator);
        lineCaptcha.createCode();
        drawCaptchaBo.setLineCaptcha(lineCaptcha);
        drawCaptchaBo.setImgBytes(lineCaptcha.getImageBytes());
        stringRedisTemplate.opsForValue().set(redisUuid, lineCaptcha.getCode(), 60 * 30, TimeUnit.SECONDS);
        return drawCaptchaBo;
    }

    @Override
    public String login(LoginMo loginMo) {
        // 先校验验证码
        if (RedisUtil.checkCodeAndValue(stringRedisTemplate, loginMo.getRedisUuid(), loginMo.getRedisValue())) {
            // 通过用户名查询数据库记录
            QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
            webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, loginMo.getUserName());
            WebUserEntity webUserEntity = webUserEntityService.getOne(webUserEntityQueryWrapper);
            if (ObjectUtil.isNotNull(webUserEntity)) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if (bCryptPasswordEncoder.matches(loginMo.getPassWord(), webUserEntity.getPassWord())) {
                    return getLoginToken(webUserEntity);
                } else {
                    throw new Exception20000("用户名密码错误");
                }
            } else {
                throw new Exception20000("用户名密码错误");
            }
        } else {
            throw new Exception20000("请输入正确的验证码");
        }
    }

    @Override
    public Boolean logout() {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        if (StrUtil.isNotBlank(userInfo.getToken())) {
            stringRedisTemplate.delete(userInfo.getToken());
        }
        return true;
    }

    private String getLoginToken(WebUserEntity webUserEntity) {
        // 将用户信息json化，放入redis 获取redis key
        String token = StrUtil.uuid();
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(webUserEntity, userInfo);
        List<String> roleCodeList = new ArrayList<String>();
        List<String> permissionCodeList = new ArrayList<String>();
        QueryWrapper<WebUserRoleEntity> webUserRoleEntityQueryWrapper = new QueryWrapper<>();
        webUserRoleEntityQueryWrapper.lambda().eq(WebUserRoleEntity::getUserId, userInfo.getId());
        List<WebUserRoleEntity> webUserRoleEntityList = webUserRoleEntityService.list(webUserRoleEntityQueryWrapper);
        if (ObjectUtil.isNotEmpty(webUserRoleEntityList)) {
            List<WebRoleEntity> webRoleEntityList = webRoleEntityService.listByIds(webUserRoleEntityList.stream().map(WebUserRoleEntity::getRoleId).collect(Collectors.toList()));
            if (ObjectUtil.isNotEmpty(webRoleEntityList)) {
                roleCodeList = webRoleEntityList.stream().map(WebRoleEntity::getCode).collect(Collectors.toList());
                QueryWrapper<WebRolePermissionEntity> webRolePermissionEntityQueryWrapper = new QueryWrapper<>();
                webRolePermissionEntityQueryWrapper.lambda().in(WebRolePermissionEntity::getRoleId, webRoleEntityList.stream().map(WebRoleEntity::getId).collect(Collectors.toList()));
                List<WebRolePermissionEntity> webRolePermissionEntityList = webRolePermissionEntityService.list(webRolePermissionEntityQueryWrapper);
                if (ObjectUtil.isNotEmpty(webRolePermissionEntityList)) {
                    List<WebPermissionEntity> webPermissionEntityList = webPermissionEntityService.listByIds(webRolePermissionEntityList.stream().map(WebRolePermissionEntity::getPermissionId).collect(Collectors.toList()));
                    if (ObjectUtil.isNotEmpty(webPermissionEntityList)) {
                        permissionCodeList = webPermissionEntityList.stream().map(WebPermissionEntity::getCode).collect(Collectors.toList());
                    }
                }
            }
        }
        userInfo.setRoleCodeList(roleCodeList);
        userInfo.setPermissionCodeList(permissionCodeList);
        userInfo.setToken(token);
        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), 60 * 30, TimeUnit.SECONDS);
        // 返回key
        return token;

    }


    @Override
    public IPage<WebUserBo> queryPageWebUser() {

        Page<WebUserEntity> page = new Page<>();
        page.setCurrent(1);
        page.setSize(10);
        QueryWrapper<WebUserEntity> queryWrapper = new QueryWrapper<>();


        page = webUserEntityService.page(page, queryWrapper);
        return page.convert(webUserEntity -> {
            WebUserBo webUserBo = new WebUserBo();
            BeanUtil.copyProperties(webUserEntity, webUserBo);
            return webUserBo;
        });
    }

    @Override
    public IPage<WebMenuBo> queryPageWebMenu() {
        return null;
    }
}
