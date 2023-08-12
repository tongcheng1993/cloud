package com.zifuji.cloud.server.sys.module.manageUser.service.impl;

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
import com.zifuji.cloud.server.sys.db.manageUser.entity.*;
import com.zifuji.cloud.server.sys.db.manageUser.service.*;
import com.zifuji.cloud.server.sys.module.captcha.bo.DrawCaptchaComponentMo;
import com.zifuji.cloud.server.sys.module.captcha.service.CaptchaService;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageMenuComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManagePermissionComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageRoleComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageUserComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.mapper.ManageUserMapper;
import com.zifuji.cloud.server.sys.module.manageUser.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageMenuPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManagePermissionPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageRolePageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageUserPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.vo.*;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import com.zifuji.cloud.server.base.util.MyBatisPlusUtil;

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
    private CaptchaService captchaService;

    @Override
    public DrawCaptchaControllerVo drawCaptcha() {
        DrawCaptchaControllerVo drawCaptchaVo = new DrawCaptchaControllerVo();
        DrawCaptchaComponentMo drawCaptchaBo = captchaService.drawCaptcha(BaseConstant.BUSINESS_TYPE_MANAGE, "img");
        BeanUtil.copyProperties(drawCaptchaBo, drawCaptchaVo);
        return drawCaptchaVo;
    }

    @Override
    public String login(LoginControllerMo loginMo) {

        captchaService.checkCodeAndValue(BaseConstant.BUSINESS_TYPE_MANAGE, "img", loginMo.getRedisUuid(), loginMo.getValue());

        // 通过用户名查询数据库记录
        QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
        manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, loginMo.getUserName());
        ManageUserEntity manageUserEntity = manageUserEntityService.getOne(manageUserEntityQueryWrapper);
        if (ObjectUtil.isNull(manageUserEntity)) {
            throw new Exception20000("用户名密码错误");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(loginMo.getPassWord(), manageUserEntity.getPassWord())) {
            throw new Exception20000("用户名密码错误");
        }
        return getLoginToken(manageUserEntity);
    }

    @Override
    public List<ManageMenuControllerVo> getMenu() {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        List<String> roleList = new ArrayList<String>();
        for (int i = 0; i < userInfo.getRoleCodeList().size(); i++) {
            roleList.add(userInfo.getRoleCodeList().get(i).replace("ROLE_", ""));
        }
        ManageMenuPageQo manageMenuPageQo = new ManageMenuPageQo();
        manageMenuPageQo.setRoleCode(roleList);
        List<ManageMenuComponentMo> manageMenuBoList = selectListMenu(manageMenuPageQo);
        List<ManageMenuControllerVo> manageMenuVoList = new ArrayList<ManageMenuControllerVo>();
        for (int i = 0; i < manageMenuBoList.size(); i++) {
            ManageMenuComponentMo menuBo = manageMenuBoList.get(i);
            ManageMenuControllerVo manageMenuVo = new ManageMenuControllerVo();
            BeanUtil.copyProperties(menuBo, manageMenuVo);
            manageMenuVoList.add(manageMenuVo);
        }
        return manageMenuVoList;
    }

    @Override
    public IPage<ManageUserControllerVo> queryPageUser(ManageUserPageQo manageUserPageQo) {
        IPage<ManageUserComponentMo> page = selectPageUser(manageUserPageQo);
        return page.convert(manageUserBo -> {
            ManageUserControllerVo manageUserVo = new ManageUserControllerVo();
            BeanUtil.copyProperties(manageUserBo, manageUserVo);
            return manageUserVo;
        });
    }

    @Override
    public List<ManageUserControllerVo> queryListUser(ManageUserPageQo manageUserPageQo) {
        return null;
    }

    @Override
    public ManageUserControllerVo saveUser(ManageUserControllerMo manageUserMo) {
        ManageUserControllerVo vo = new ManageUserControllerVo();
        if (ObjectUtil.isNull(manageUserMo.getId())) {
            // 通过用户名查询数据库记录
            QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
            manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, manageUserMo.getUserName());
            ManageUserEntity manageUserEntity = this.manageUserEntityService.getOne(manageUserEntityQueryWrapper);
            if (ObjectUtil.isNotNull(manageUserEntity)) {
                throw new Exception20000("用户名重复");
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
                throw new Exception20000("编辑数据错误");
            }
            manageUserEntity.setUserName(manageUserMo.getUserName());
            manageUserEntity.setName(manageUserMo.getName());
            manageUserEntityService.updateById(manageUserEntity);
            BeanUtil.copyProperties(manageUserEntity, vo);
            return vo;
        }


    }

    @Override
    public ManageUserControllerVo resetPassWord(ResetPassWordControllerMo resetPassWordMo) {
        ManageUserControllerVo vo = new ManageUserControllerVo();
        ManageUserEntity manageUserEntity = this.manageUserEntityService.getById(resetPassWordMo.getId());
        if (ObjectUtil.isNull(manageUserEntity)) {
            throw new Exception20000("编辑数据错误");
        }
        // 密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        manageUserEntity.setPassWord(bCryptPasswordEncoder.encode(resetPassWordMo.getPassWord()));
        manageUserEntityService.updateById(manageUserEntity);
        BeanUtil.copyProperties(manageUserEntity, vo);
        return vo;
    }

    @Override
    public IPage<ManageRoleControllerVo> queryPageRole(ManageRolePageQo manageRolePageQo) {
        IPage<ManageRoleComponentMo> page = selectPageRole(manageRolePageQo);
        return page.convert(manageRoleBo -> {
            ManageRoleControllerVo manageRoleVo = new ManageRoleControllerVo();
            BeanUtil.copyProperties(manageRoleBo, manageRoleVo);
            return manageRoleVo;
        });
    }

    @Override
    public String saveRole(ManageRoleControllerMo manageRoleMo) {
        if (ObjectUtil.isNull(manageRoleMo.getId())) {
            QueryWrapper<ManageRoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(ManageRoleEntity::getCode,manageRoleMo.getCode());
            ManageRoleEntity manageRoleEntity = manageRoleEntityService.getOne(queryWrapper);
            if(ObjectUtil.isNull(manageRoleEntity)){
                manageRoleEntity= new ManageRoleEntity();
                BeanUtil.copyProperties(manageRoleMo,manageRoleEntity);
                manageRoleEntityService.save(manageRoleEntity);
            }else{

            }
        } else {

        }
        return null;
    }



    @Override
    public List<ManageRoleControllerVo> queryListRole(ManageRolePageQo manageRolePageQo) {
        List<ManageRoleComponentMo> list = selectListRole(manageRolePageQo);
        return list.stream().map(manageRoleBo -> {
            ManageRoleControllerVo manageRoleVo = new ManageRoleControllerVo();
            BeanUtil.copyProperties(manageRoleBo, manageRoleVo);
            return manageRoleVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ManagePermissionControllerVo> queryListPermission(ManagePermissionPageQo managePermissionPageQo) {
        List<ManagePermissionComponentMo> list = selectListPermission(managePermissionPageQo);
        return list.stream().map(managePermissionBo -> {
            ManagePermissionControllerVo managePermissionVo = new ManagePermissionControllerVo();
            BeanUtil.copyProperties(managePermissionBo, managePermissionVo);
            return managePermissionVo;
        }).collect(Collectors.toList());
    }

    @Override
    public IPage<ManagePermissionControllerVo> queryPagePermission(ManagePermissionPageQo managePermissionPageQo) {

        IPage<ManagePermissionComponentMo> page = selectPagePermission(managePermissionPageQo);
        return page.convert(managePermissionBo -> {
            ManagePermissionControllerVo managePermissionVo = new ManagePermissionControllerVo();
            BeanUtil.copyProperties(managePermissionBo, managePermissionVo);
            return managePermissionVo;
        });
    }

    @Override
    public String savePermission(ManagePermissionControllerMo managePermissionMo) {
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
    public List<ManageMenuControllerVo> queryListMenu(ManageMenuPageQo manageMenuPageQo) {
        List<ManageMenuComponentMo> list = selectListMenu(manageMenuPageQo);

        return list.stream().map(manageMenuBo -> {
            ManageMenuControllerVo manageMenuVo = new ManageMenuControllerVo();
            BeanUtil.copyProperties(manageMenuBo, manageMenuVo);
            return manageMenuVo;
        }).collect(Collectors.toList());

    }


    @Override
    public String saveMenu(ManageMenuControllerMo manageMenuMo) {
        ManageMenuEntity manageMenuEntity = null;
        if (ObjectUtil.isNull(manageMenuMo.getId())) {
            QueryWrapper<ManageMenuEntity> queryWrapper = new QueryWrapper<ManageMenuEntity>();
            queryWrapper.lambda().eq(ManageMenuEntity::getPath, manageMenuMo.getPath());
            manageMenuEntity = manageMenuEntityService.getOne(queryWrapper);
            if (ObjectUtil.isNotNull(manageMenuEntity)) {
                throw new Exception20000("当前路径重复");
            } else {
                manageMenuEntity = new ManageMenuEntity();
            }
            BeanUtil.copyProperties(manageMenuMo, manageMenuEntity);
            manageMenuEntityService.save(manageMenuEntity);
        } else {
            manageMenuEntity = manageMenuEntityService.getById(manageMenuMo.getId());
            if (ObjectUtil.isNull(manageMenuEntity)) {
                throw new Exception20000("编辑数据错误");
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
                            throw new Exception20000("当前路径重复");
                        }
                    }
                }

            }
        }


        return manageMenuMo.getId() + "";
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
    public String saveUserRoleRelation(ManageUserRoleRelationControllerMo manageUserRoleRelationMo) {
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
    public String saveRolePermissionRelation(ManageRolePermissionRelationControllerMo manageRolePermissionRelationMo) {
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
    public String saveRoleMenuRelation(ManageRoleMenuRelationControllerMo manageRoleMenuRelationMo) {
        if (ObjectUtil.isNull(manageRoleMenuRelationMo.getRoleId()) || manageRoleMenuRelationMo.getRoleId().equals(0L)) {
            throw new Exception20000("");
        }
        if (ObjectUtil.isEmpty(manageRoleMenuRelationMo.getMenuIdList())) {
            QueryWrapper<ManageRoleMenuEntity> queryWrapper = new QueryWrapper<ManageRoleMenuEntity>();
            queryWrapper.lambda().eq(ManageRoleMenuEntity::getRoleId, manageRoleMenuRelationMo.getRoleId());
            manageRoleMenuEntityService.remove(queryWrapper);
        } else {
            for (Long menuId : manageRoleMenuRelationMo.getMenuIdList()) {
                saveRoleMenuRelation(manageRoleMenuRelationMo.getRoleId(), menuId);
            }
        }
        return manageRoleMenuRelationMo.getRoleId() + "";
    }

    @Override
    public String saveRoleMenuRelation(Long roleId, Long menuId) {
        QueryWrapper<ManageRoleMenuEntity> queryWrapper = new QueryWrapper<ManageRoleMenuEntity>();
        queryWrapper.lambda()
                .eq(ManageRoleMenuEntity::getRoleId, roleId)
                .eq(ManageRoleMenuEntity::getMenuId, menuId);
        ManageRoleMenuEntity manageRoleMenuEntity = manageRoleMenuEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(manageRoleMenuEntity)) {
            manageRoleMenuEntity = new ManageRoleMenuEntity();
            manageRoleMenuEntity.setRoleId(roleId);
            manageRoleMenuEntity.setMenuId(menuId);
            manageRoleMenuEntityService.save(manageRoleMenuEntity);
        }
        return manageRoleMenuEntity.getId() + "";
    }

    @Override
    public String saveRoleMenuRelation(String roleCode, Long menuId) {


        return null;
    }

    @Override
    public Boolean initManage() {


        return null;
    }


    private String getLoginToken(ManageUserEntity manageUserEntity) {
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(manageUserEntity, userInfo);
        List<String> roleCodeList = new ArrayList<>();
        List<String> permissionCodeList = new ArrayList<>();

        ManageRolePageQo manageRolePageQo = new ManageRolePageQo();
        manageRolePageQo.setUserId(new ArrayList<Long>());
        manageRolePageQo.getUserId().add(userInfo.getId());
        List<ManageRoleComponentMo> manageRoleBoList = selectListRole(manageRolePageQo);
        for (ManageRoleComponentMo manageRoleBo : manageRoleBoList) {
            roleCodeList.add(manageRoleBo.getCode());
        }

        ManagePermissionPageQo managePermissionPageQo = new ManagePermissionPageQo();
        managePermissionPageQo.setUserId(new ArrayList<Long>());
        managePermissionPageQo.getUserId().add(userInfo.getId());
        List<ManagePermissionComponentMo> managePermissionBoList = selectListPermission(managePermissionPageQo);
        for (ManagePermissionComponentMo managePermissionBo : managePermissionBoList) {
            permissionCodeList.add(managePermissionBo.getCodeSys() + ":" + managePermissionBo.getCodeModule() + ":" + managePermissionBo.getCode());
        }


        userInfo.setRoleCodeList(roleCodeList);
        userInfo.setPermissionCodeList(permissionCodeList);
        return getLoginToken(userInfo);
    }

    private String getLoginToken(UserInfo userInfo) {
        // 将用户信息json化，放入redis 获取redis key
        String token = StrUtil.uuid();

        userInfo.setToken(token);

        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), 60 * 30, TimeUnit.SECONDS);
        // 返回key
        return token;
    }


    private QueryWrapper<ManageUserComponentMo> getManageUserBoQueryWrapper(ManageUserPageQo manageUserPageQo) {
        QueryWrapper<ManageUserComponentMo> queryWrapper = new QueryWrapper<ManageUserComponentMo>();
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

    private List<ManageUserComponentMo> selectListUser(ManageUserPageQo manageUserPageQo) {
        return manageUserMapper.selectListUser(getManageUserBoQueryWrapper(manageUserPageQo));
    }

    private IPage<ManageUserComponentMo> selectPageUser(ManageUserPageQo manageUserPageQo) {
        Page<ManageUserComponentMo> page = new Page<ManageUserComponentMo>(manageUserPageQo.getCurrent(), manageUserPageQo.getSize());
        return manageUserMapper.selectPageUser(page, getManageUserBoQueryWrapper(manageUserPageQo));
    }

    private QueryWrapper<ManageRoleComponentMo> getManageRoleBoQueryWrapper(ManageRolePageQo manageRolePageQo) {
        QueryWrapper<ManageRoleComponentMo> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(manageRolePageQo.getUserId())) {
            queryWrapper.in("zmu.id", manageRolePageQo.getUserId());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, manageRolePageQo.getOrders());
        queryWrapper.groupBy("id");
        return queryWrapper;
    }

    private List<ManageRoleComponentMo> selectListRole(ManageRolePageQo manageRolePageQo) {
        return manageUserMapper.selectListRole(getManageRoleBoQueryWrapper(manageRolePageQo));
    }


    private IPage<ManageRoleComponentMo> selectPageRole(ManageRolePageQo manageRolePageQo) {
        Page<ManageRoleComponentMo> page = new Page<ManageRoleComponentMo>(manageRolePageQo.getCurrent(), manageRolePageQo.getSize());
        return manageUserMapper.selectPageRole(page, getManageRoleBoQueryWrapper(manageRolePageQo));
    }

    private QueryWrapper<ManagePermissionComponentMo> getManagePermissionBoQueryWrapper(ManagePermissionPageQo managePermissionPageQo) {
        QueryWrapper<ManagePermissionComponentMo> queryWrapper = new QueryWrapper<>();
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

    private List<ManagePermissionComponentMo> selectListPermission(ManagePermissionPageQo managePermissionPageQo) {
        return manageUserMapper.selectListPermission(getManagePermissionBoQueryWrapper(managePermissionPageQo));
    }

    private IPage<ManagePermissionComponentMo> selectPagePermission(ManagePermissionPageQo managePermissionPageQo) {
        Page<ManagePermissionComponentMo> page = new Page<ManagePermissionComponentMo>(managePermissionPageQo.getCurrent(), managePermissionPageQo.getSize());
        return manageUserMapper.selectPagePermission(page, getManagePermissionBoQueryWrapper(managePermissionPageQo));
    }

    private QueryWrapper<ManageMenuComponentMo> getManageMenuBoQueryWrapper(ManageMenuPageQo manageMenuPageQo) {
        QueryWrapper<ManageMenuComponentMo> queryWrapper = new QueryWrapper<>();
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

    private List<ManageMenuComponentMo> selectListMenu(ManageMenuPageQo manageMenuPageQo) {
        return manageUserMapper.selectListMenu(getManageMenuBoQueryWrapper(manageMenuPageQo));
    }

}
