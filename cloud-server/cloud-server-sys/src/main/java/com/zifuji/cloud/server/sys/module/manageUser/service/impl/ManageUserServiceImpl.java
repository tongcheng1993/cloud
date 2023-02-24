package com.zifuji.cloud.server.sys.module.manageUser.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.manageUser.entity.*;
import com.zifuji.cloud.server.sys.db.manageUser.service.*;
import com.zifuji.cloud.server.sys.module.core.bo.DrawCaptchaBo;
import com.zifuji.cloud.server.sys.module.core.service.CoreService;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageMenuBo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManagePermissionBo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageRoleBo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageUserBo;
import com.zifuji.cloud.server.sys.module.manageUser.mapper.ManageUserMapper;
import com.zifuji.cloud.server.sys.module.manageUser.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageMenuPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManagePermissionPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageRolePageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageUserPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.vo.*;
import com.zifuji.cloud.starter.web.object.SecurityUtil;
import com.zifuji.cloud.starter.web.util.MyBatisPlusUtil;

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
public class ManageUserServiceImpl implements ManageUserService {

    private ManageUserMapper manageUserMapper;
    private StringRedisTemplate stringRedisTemplate;
    private ManageUserEntityService manageUserEntityService;
    private ManageRoleEntityService manageRoleEntityService;
    private ManagePermissionEntityService managePermissionEntityService;
    private ManageMenuEntityService manageMenuEntityService;
    private ManageUserRoleEntityService manageUserRoleEntityService;
    private ManageRoleMenuEntityService manageRoleMenuEntityService;
    private ManageRolePermissionEntityService manageRolePermissionEntityService;
    private CoreService coreService;

    @Override
    public DrawCaptchaVo drawCaptcha() {
        DrawCaptchaVo drawCaptchaVo = new DrawCaptchaVo();
        DrawCaptchaBo drawCaptchaBo = coreService.drawCaptcha(BaseConstant.BUSINESS_TYPE_MANAGE,"img");
        BeanUtil.copyProperties(drawCaptchaBo, drawCaptchaVo);
        return drawCaptchaVo;
    }

    @Override
    public String login(LoginMo loginMo) {

        coreService.checkCodeAndValue(BaseConstant.BUSINESS_TYPE_MANAGE,"img",loginMo.getRedisUuid(),loginMo.getValue());

        // 通过用户名查询数据库记录
        QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
        manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, loginMo.getUserName());
        ManageUserEntity manageUserEntity = manageUserEntityService.getOne(manageUserEntityQueryWrapper);
        if (ObjectUtil.isNull(manageUserEntity)) {
            throw new Exception200("用户名密码错误");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(loginMo.getPassWord(), manageUserEntity.getPassWord())) {
            throw new Exception200("用户名密码错误");
        }
        return getLoginToken(manageUserEntity);
    }

    @Override
    public List<ManageMenuVo> getMenu() {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        List<String> roleList = new ArrayList<String>();
        for (int i = 0; i < userInfo.getRoleList().size(); i++) {
            roleList.add(userInfo.getRoleList().get(i).replace("ROLE_", ""));
        }
        ManageMenuPageQo manageMenuPageQo = new ManageMenuPageQo();
        manageMenuPageQo.setRoleCode(roleList);
        List<ManageMenuBo> manageMenuBoList = selectListMenu(manageMenuPageQo);
        List<ManageMenuVo> manageMenuVoList = new ArrayList<ManageMenuVo>();
        for (int i = 0; i < manageMenuBoList.size(); i++) {
            ManageMenuBo menuBo = manageMenuBoList.get(i);
            ManageMenuVo manageMenuVo = new ManageMenuVo();
            BeanUtil.copyProperties(menuBo, manageMenuVo);
            manageMenuVoList.add(manageMenuVo);
        }
        return manageMenuVoList;
    }

    @Override
    public IPage<ManageUserVo> queryPageUser(ManageUserPageQo manageUserPageQo) {
        IPage<ManageUserBo> page = selectPageUser(manageUserPageQo);
        return page.convert(manageUserBo -> {
            ManageUserVo manageUserVo = new ManageUserVo();
            BeanUtil.copyProperties(manageUserBo, manageUserVo);
            return manageUserVo;
        });
    }

    @Override
    public List<ManageUserVo> queryListUser(ManageUserPageQo manageUserPageQo) {
        return null;
    }

    @Override
    public ManageUserVo saveUser(ManageUserMo manageUserMo) {
        ManageUserVo vo = new ManageUserVo();
        if (ObjectUtil.isNull(manageUserMo.getId())) {
            // 通过用户名查询数据库记录
            QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
            manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, manageUserMo.getUserName());
            ManageUserEntity manageUserEntity = this.manageUserEntityService.getOne(manageUserEntityQueryWrapper);
            if (ObjectUtil.isNotNull(manageUserEntity)) {
                throw new Exception200("用户名重复");
            }
            manageUserEntity = new ManageUserEntity();

            // 密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            manageUserEntity.setUserName(manageUserMo.getUserName());
            manageUserEntity.setPassWord(bCryptPasswordEncoder.encode(manageUserMo.getPassWord()));
            manageUserEntity.setName(manageUserMo.getName());

            manageUserEntityService.save(manageUserEntity);
            this.saveUserAndRole(manageUserEntity.getId(), BaseConstant.ROLE_BASE);
            BeanUtil.copyProperties(manageUserEntity, vo);
            return vo;
        } else {
            ManageUserEntity manageUserEntity = manageUserEntityService.getById(manageUserMo.getId());
            if (ObjectUtil.isNull(manageUserEntity)) {
                throw new Exception200("编辑数据错误");
            }
            manageUserEntity.setUserName(manageUserMo.getUserName());
            manageUserEntity.setName(manageUserMo.getName());
            manageUserEntityService.updateById(manageUserEntity);
            BeanUtil.copyProperties(manageUserEntity, vo);
            return vo;
        }


    }

    @Override
    public ManageUserVo resetPassWord(ResetPassWordMo resetPassWordMo) {
        ManageUserVo vo = new ManageUserVo();
        ManageUserEntity manageUserEntity = this.manageUserEntityService.getById(resetPassWordMo.getId());
        if (ObjectUtil.isNull(manageUserEntity)) {
            throw new Exception200("编辑数据错误");
        }
        // 密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        manageUserEntity.setPassWord(bCryptPasswordEncoder.encode(resetPassWordMo.getPassWord()));
        manageUserEntityService.updateById(manageUserEntity);
        BeanUtil.copyProperties(manageUserEntity, vo);
        return vo;
    }

    @Override
    public IPage<ManageRoleVo> queryPageRole(ManageRolePageQo manageRolePageQo) {
        IPage<ManageRoleBo> page = selectPageRole(manageRolePageQo);
        return page.convert(manageRoleBo -> {
            ManageRoleVo manageRoleVo = new ManageRoleVo();
            BeanUtil.copyProperties(manageRoleBo, manageRoleVo);
            return manageRoleVo;
        });
    }

    @Override
    public List<ManageRoleVo> queryListRole(ManageRolePageQo manageRolePageQo) {
        List<ManageRoleBo> list = selectListRole(manageRolePageQo);
        return list.stream().map(manageRoleBo -> {
            ManageRoleVo manageRoleVo = new ManageRoleVo();
            BeanUtil.copyProperties(manageRoleBo, manageRoleVo);
            return manageRoleVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ManagePermissionVo> queryListPermission(ManagePermissionPageQo managePermissionPageQo) {
        List<ManagePermissionBo> list = selectListPermission(managePermissionPageQo);
        return list.stream().map(managePermissionBo -> {
            ManagePermissionVo managePermissionVo = new ManagePermissionVo();
            BeanUtil.copyProperties(managePermissionBo, managePermissionVo);
            return managePermissionVo;
        }).collect(Collectors.toList());
    }

    @Override
    public IPage<ManagePermissionVo> queryPagePermission(ManagePermissionPageQo managePermissionPageQo) {

        IPage<ManagePermissionBo> page = selectPagePermission(managePermissionPageQo);
        return page.convert(managePermissionBo -> {
            ManagePermissionVo managePermissionVo = new ManagePermissionVo();
            BeanUtil.copyProperties(managePermissionBo, managePermissionVo);
            return managePermissionVo;
        });
    }

    @Override
    public String savePermission(ManagePermissionMo managePermissionMo) {
        if (ObjectUtil.isNull(managePermissionMo.getId())) {
            ManagePermissionEntity managePermissionEntity = new ManagePermissionEntity();
            BeanUtil.copyProperties(managePermissionMo, managePermissionEntity);
            managePermissionEntityService.save(managePermissionEntity);
            return managePermissionEntity.getId() + "";
        } else {
            return managePermissionMo.getId() + "";
        }
    }

    ;

    @Override
    public List<ManageMenuVo> queryListMenu(ManageMenuPageQo manageMenuPageQo) {
        List<ManageMenuBo> list = selectListMenu(manageMenuPageQo);

        return list.stream().map(manageMenuBo -> {
            ManageMenuVo manageMenuVo = new ManageMenuVo();
            BeanUtil.copyProperties(manageMenuBo, manageMenuVo);
            return manageMenuVo;
        }).collect(Collectors.toList());

    }


    @Override
    public String saveMenu(ManageMenuMo manageMenuMo) {
        if (ObjectUtil.isNull(manageMenuMo.getId())) {
            QueryWrapper<ManageMenuEntity> queryWrapper = new QueryWrapper<ManageMenuEntity>();
            queryWrapper.lambda().eq(ManageMenuEntity::getPath, manageMenuMo.getPath());
            ManageMenuEntity manageMenuEntity = manageMenuEntityService.getOne(queryWrapper);
            if (ObjectUtil.isNotNull(manageMenuEntity)) {
                throw new Exception200("当前路径重复");
            } else {
                manageMenuEntity = new ManageMenuEntity();
            }
            BeanUtil.copyProperties(manageMenuMo, manageMenuEntity);
            manageMenuEntityService.save(manageMenuEntity);
            return manageMenuEntity.getId() + "";
        } else {
            ManageMenuEntity manageMenuEntity = manageMenuEntityService.getById(manageMenuMo.getId());
            if (ObjectUtil.isNull(manageMenuEntity)) {
                throw new Exception200("编辑数据错误");
            } else {
                QueryWrapper<ManageMenuEntity> queryWrapper = new QueryWrapper<ManageMenuEntity>();
                queryWrapper.lambda().eq(ManageMenuEntity::getPath, manageMenuMo.getPath());
                List<ManageMenuEntity> manageMenuEntityList = manageMenuEntityService.list(queryWrapper);
                if (ObjectUtil.isEmpty(manageMenuEntityList)) {
                    BeanUtil.copyProperties(manageMenuMo, manageMenuEntity);
                    manageMenuEntityService.updateById(manageMenuEntity);
                } else {
                    if (manageMenuEntityList.size() == 1) {
                        if (manageMenuEntityList.get(0).getId().equals(manageMenuMo.getId())) {
                            BeanUtil.copyProperties(manageMenuMo, manageMenuEntity);
                            manageMenuEntityService.updateById(manageMenuEntity);
                        } else {
                            throw new Exception200("当前路径重复");
                        }
                    }
                }

            }
            return manageMenuMo.getId() + "";
        }
    }

    @Override
    public String saveUserAndRole(Long userId, String roleCode) {
        QueryWrapper<ManageRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ManageRoleEntity::getCode, roleCode);
        ManageRoleEntity manageRoleEntity = manageRoleEntityService.getOne(queryWrapper);
        return this.saveUserAndRole(userId, manageRoleEntity.getId());
    }

    @Override
    public String saveUserAndRole(Long userId, Long roleId) {
        QueryWrapper<ManageUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ManageUserRoleEntity::getUserId, userId).eq(ManageUserRoleEntity::getRoleId, roleId);
        ManageUserRoleEntity manageUserRoleEntity = manageUserRoleEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(manageUserRoleEntity)) {
            manageUserRoleEntity = new ManageUserRoleEntity();
            manageUserRoleEntity.setUserId(userId);
            manageUserRoleEntity.setRoleId(roleId);
            manageUserRoleEntityService.save(manageUserRoleEntity);
        }
        return manageUserRoleEntity.getId() + "";
    }

    @Override
    public String saveUserRoleRelation(ManageUserRoleRelationMo manageUserRoleRelationMo) {
        QueryWrapper<ManageUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ManageUserRoleEntity::getUserId, manageUserRoleRelationMo.getUserId());
        manageUserRoleEntityService.remove(queryWrapper);
        List<ManageUserRoleEntity> list = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(manageUserRoleRelationMo.getRoleIdList())) {
            for (Long roleId : manageUserRoleRelationMo.getRoleIdList()) {
                ManageUserRoleEntity manageUserRoleEntity = new ManageUserRoleEntity();
                manageUserRoleEntity.setUserId(manageUserRoleRelationMo.getUserId());
                manageUserRoleEntity.setRoleId(roleId);
                list.add(manageUserRoleEntity);
            }
        }
        manageUserRoleEntityService.saveBatch(list);
        return manageUserRoleRelationMo.getUserId() + "";
    }

    @Override
    public String saveRolePermissionRelation(ManageRolePermissionRelationMo manageRolePermissionRelationMo) {
        QueryWrapper<ManageRolePermissionEntity> queryWrapper = new QueryWrapper<ManageRolePermissionEntity>();
        queryWrapper.lambda().eq(ManageRolePermissionEntity::getRoleId, manageRolePermissionRelationMo.getRoleId());
        manageRolePermissionEntityService.remove(queryWrapper);
        List<ManageRolePermissionEntity> list = new ArrayList<ManageRolePermissionEntity>();
        if (ObjectUtil.isNotEmpty(manageRolePermissionRelationMo.getPermissionIdList())) {
            for (Long permissionId : manageRolePermissionRelationMo.getPermissionIdList()) {
                ManageRolePermissionEntity manageRolePermissionEntity = new ManageRolePermissionEntity();
                manageRolePermissionEntity.setRoleId(manageRolePermissionRelationMo.getRoleId());
                manageRolePermissionEntity.setPermissionId(permissionId);
                list.add(manageRolePermissionEntity);
            }
        }
        manageRolePermissionEntityService.saveBatch(list);
        return manageRolePermissionRelationMo.getRoleId() + "";
    }

    @Override
    public String saveRoleMenuRelation(ManageRoleMenuRelationMo manageRoleMenuRelationMo) {
        QueryWrapper<ManageRoleMenuEntity> queryWrapper = new QueryWrapper<ManageRoleMenuEntity>();
        queryWrapper.lambda().eq(ManageRoleMenuEntity::getRoleId, manageRoleMenuRelationMo.getRoleId());
        manageRoleMenuEntityService.remove(queryWrapper);
        List<ManageRoleMenuEntity> list = new ArrayList<ManageRoleMenuEntity>();
        if (ObjectUtil.isNotEmpty(manageRoleMenuRelationMo.getMenuIdList())) {
            for (Long menuId : manageRoleMenuRelationMo.getMenuIdList()) {
                ManageRoleMenuEntity manageRoleMenuEntity = new ManageRoleMenuEntity();
                manageRoleMenuEntity.setRoleId(manageRoleMenuRelationMo.getRoleId());
                manageRoleMenuEntity.setMenuId(menuId);
                list.add(manageRoleMenuEntity);
            }
        }
        manageRoleMenuEntityService.saveBatch(list);
        return manageRoleMenuRelationMo.getRoleId() + "";

    }


    private void checkCodeAndValue(String businessCode, String code, String value) {
        // 先校验验证码
        String tempValue = stringRedisTemplate.opsForValue().get(businessCode + code);
        if (StrUtil.isNotBlank(tempValue) && StrUtil.equalsIgnoreCase(tempValue, value)) {
            stringRedisTemplate.delete(code);
        } else {
            throw new Exception200("验证码错误");
        }
    }

    private String getLoginToken(ManageUserEntity manageUserEntity) {
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(manageUserEntity, userInfo);
        List<String> roleCodeList = new ArrayList<>();
        List<String> permissionCodeList = new ArrayList<>();

        ManageRolePageQo manageRolePageQo = new ManageRolePageQo();
        manageRolePageQo.setUserId(new ArrayList<Long>());
        manageRolePageQo.getUserId().add(userInfo.getId());
        List<ManageRoleBo> manageRoleBoList = selectListRole(manageRolePageQo);
        for (ManageRoleBo manageRoleBo : manageRoleBoList) {
            roleCodeList.add(manageRoleBo.getCode());
        }

        ManagePermissionPageQo managePermissionPageQo = new ManagePermissionPageQo();
        managePermissionPageQo.setUserId(new ArrayList<Long>());
        managePermissionPageQo.getUserId().add(userInfo.getId());
        List<ManagePermissionBo> managePermissionBoList = selectListPermission(managePermissionPageQo);
        for (ManagePermissionBo managePermissionBo : managePermissionBoList) {
            permissionCodeList.add(managePermissionBo.getCodeSys() + ":" + managePermissionBo.getCodeModule() + ":" + managePermissionBo.getCode());
        }


        userInfo.setRoleList(roleCodeList);
        userInfo.setPermissionList(permissionCodeList);
        return getLoginToken(userInfo);
    }

    private String getLoginToken(UserInfo userInfo) {
        // 将用户信息json化，放入redis 获取redis key
        String token = StrUtil.uuid();
        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), 60 * 30, TimeUnit.SECONDS);
        // 返回key
        return token;
    }


    private QueryWrapper<ManageUserBo> getManageUserBoQueryWrapper(ManageUserPageQo manageUserPageQo) {
        QueryWrapper<ManageUserBo> queryWrapper = new QueryWrapper<ManageUserBo>();
        if (StrUtil.isNotBlank(manageUserPageQo.getUserName())) {
            queryWrapper.like("zmu.user_name", manageUserPageQo.getUserName());
        }
        if (StrUtil.isNotBlank(manageUserPageQo.getName())) {
            queryWrapper.like("zmu.name", manageUserPageQo.getName());
        }
        if (ObjectUtil.isNotEmpty(manageUserPageQo.getRoleName())) {
            queryWrapper.in("zmr.name", manageUserPageQo.getRoleName());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, manageUserPageQo.getOrders());
        queryWrapper.groupBy("id");
        return queryWrapper;
    }

    private List<ManageUserBo> selectListUser(ManageUserPageQo manageUserPageQo) {
        return manageUserMapper.selectListUser(getManageUserBoQueryWrapper(manageUserPageQo));
    }

    private IPage<ManageUserBo> selectPageUser(ManageUserPageQo manageUserPageQo) {
        Page<ManageUserBo> page = new Page<ManageUserBo>(manageUserPageQo.getCurrent(), manageUserPageQo.getSize());
        return manageUserMapper.selectPageUser(page, getManageUserBoQueryWrapper(manageUserPageQo));
    }

    private QueryWrapper<ManageRoleBo> getManageRoleBoQueryWrapper(ManageRolePageQo manageRolePageQo) {
        QueryWrapper<ManageRoleBo> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(manageRolePageQo.getUserId())) {
            queryWrapper.in("zmu.id", manageRolePageQo.getUserId());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, manageRolePageQo.getOrders());
        queryWrapper.groupBy("id");
        return queryWrapper;
    }

    private List<ManageRoleBo> selectListRole(ManageRolePageQo manageRolePageQo) {
        return manageUserMapper.selectListRole(getManageRoleBoQueryWrapper(manageRolePageQo));
    }


    private IPage<ManageRoleBo> selectPageRole(ManageRolePageQo manageRolePageQo) {
        Page<ManageRoleBo> page = new Page<ManageRoleBo>(manageRolePageQo.getCurrent(), manageRolePageQo.getSize());
        return manageUserMapper.selectPageRole(page, getManageRoleBoQueryWrapper(manageRolePageQo));
    }

    private QueryWrapper<ManagePermissionBo> getManagePermissionBoQueryWrapper(ManagePermissionPageQo managePermissionPageQo) {
        QueryWrapper<ManagePermissionBo> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(managePermissionPageQo.getUserId())) {
            queryWrapper.in("zmu.id", managePermissionPageQo.getUserId());
        }
        if (ObjectUtil.isNotEmpty(managePermissionPageQo.getRoleCode())) {
            queryWrapper.in("zmr.code", managePermissionPageQo.getRoleCode());
        }
        queryWrapper.groupBy("id");
        MyBatisPlusUtil.orderWrapper(queryWrapper, managePermissionPageQo.getOrders());
        return queryWrapper;
    }

    private List<ManagePermissionBo> selectListPermission(ManagePermissionPageQo managePermissionPageQo) {
        return manageUserMapper.selectListPermission(getManagePermissionBoQueryWrapper(managePermissionPageQo));
    }

    private IPage<ManagePermissionBo> selectPagePermission(ManagePermissionPageQo managePermissionPageQo) {
        Page<ManagePermissionBo> page = new Page<ManagePermissionBo>(managePermissionPageQo.getCurrent(), managePermissionPageQo.getSize());
        return manageUserMapper.selectPagePermission(page, getManagePermissionBoQueryWrapper(managePermissionPageQo));
    }

    private QueryWrapper<ManageMenuBo> getManageMenuBoQueryWrapper(ManageMenuPageQo manageMenuPageQo) {
        QueryWrapper<ManageMenuBo> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(manageMenuPageQo.getUserName())) {
            queryWrapper.in("zmu.user_name", manageMenuPageQo.getUserName());
        }
        if (ObjectUtil.isNotEmpty(manageMenuPageQo.getRoleCode())) {
            queryWrapper.in("zmr.code", manageMenuPageQo.getRoleCode());
        }
        queryWrapper.groupBy("id");
        MyBatisPlusUtil.orderWrapper(queryWrapper, manageMenuPageQo.getOrders());
        return queryWrapper;
    }

    private List<ManageMenuBo> selectListMenu(ManageMenuPageQo manageMenuPageQo) {
        return manageUserMapper.selectListMenu(getManageMenuBoQueryWrapper(manageMenuPageQo));
    }

}
