package com.zifuji.cloud.server.sys.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.sys.db.manageUser.entity.*;
import com.zifuji.cloud.server.sys.db.manageUser.service.*;
import com.zifuji.cloud.server.sys.module.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ServerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ManageUserEntityService manageUserEntityService;
    @Autowired
    private ManageRoleEntityService manageRoleEntityService;
    @Autowired
    private ManageMenuEntityService manageMenuEntityService;
    @Autowired
    private ManageUserRoleEntityService manageUserRoleEntityService;
    @Autowired
    private ManageRoleMenuEntityService manageRoleMenuEntityService;

    @Test
    public void ServerInitTest() {
        log.info("ServerInitTest");
    }

    public void init() {
        log.info("ServerInitTest-init");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(110L);
        String token = "110";
        List<GrantedAuthority> list = new ArrayList<>();
        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userInfo, token,
                list);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authResult);

        ManageUserEntity manageUserEntity = new ManageUserEntity();
        manageUserEntity.setUserName("tongcheng");
        // 密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        manageUserEntity.setPassWord(bCryptPasswordEncoder.encode("TONG1!cheng"));
        manageUserEntity.setName("童诚");
        manageUserEntityService.save(manageUserEntity);
    }

    public void initManageMenu(){

    }

    public void addManageRole() {
        QueryWrapper<ManageUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ManageUserEntity::getUserName, "tongcheng");
        ManageUserEntity manageUserEntity = manageUserEntityService.getOne(queryWrapper);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(manageUserEntity.getId());
        String token = "110";
        List<GrantedAuthority> list = new ArrayList<>();
        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userInfo, token,
                list);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authResult);


        ManageRoleEntity adminRole = new ManageRoleEntity();
        adminRole.setName("管理员");
        adminRole.setCode("admin");
        adminRole.setDescription("");
        manageRoleEntityService.save(adminRole);

        ManageUserRoleEntity manageUserRoleEntity = new ManageUserRoleEntity();
        manageUserRoleEntity.setUserId(manageUserEntity.getId());
        manageUserRoleEntity.setRoleId(adminRole.getId());
        manageUserRoleEntityService.save(manageUserRoleEntity);

        List<ManageMenuEntity> menuList = new ArrayList<>();


        ManageMenuEntity dashMenu = new ManageMenuEntity();
        dashMenu.setParentId(0L);
        dashMenu.setName("首页");
        dashMenu.setPath("/dashboard");
        dashMenu.setComponent("/dashboard/dashboard");
        dashMenu.setShowFlag(1);
        dashMenu.setIconFlag("1");
        manageMenuEntityService.save(dashMenu);
        menuList.add(dashMenu);


        ManageMenuEntity sysMenu = new ManageMenuEntity();
        sysMenu.setParentId(0L);
        sysMenu.setName("系统管理");
        sysMenu.setPath("/sys");
        sysMenu.setComponent("/layout/blank");
        sysMenu.setShowFlag(1);
        sysMenu.setIconFlag("1");
        manageMenuEntityService.save(sysMenu);
        menuList.add(sysMenu);

        ManageMenuEntity userListMenu = new ManageMenuEntity();
        userListMenu.setParentId(sysMenu.getId());
        userListMenu.setName("内网角色管理");
        userListMenu.setPath("/sys/manageUser");
        userListMenu.setComponent("/layout/blank");
        userListMenu.setShowFlag(1);
        userListMenu.setIconFlag("1");
        manageMenuEntityService.save(userListMenu);
        menuList.add(userListMenu);


        ManageMenuEntity roleListMenu = new ManageMenuEntity();
        roleListMenu.setParentId(sysMenu.getId());
        roleListMenu.setName("内网角色管理");
        roleListMenu.setPath("/sys/manageRole");
        roleListMenu.setComponent("/layout/blank");
        roleListMenu.setShowFlag(1);
        roleListMenu.setIconFlag("1");
        manageMenuEntityService.save(roleListMenu);
        menuList.add(roleListMenu);

        ManageMenuEntity menuListMenu = new ManageMenuEntity();
        menuListMenu.setParentId(sysMenu.getId());
        menuListMenu.setName("内网界面路由管理");
        menuListMenu.setPath("/sys/manageMenu");
        menuListMenu.setComponent("/layout/blank");
        menuListMenu.setShowFlag(1);
        menuListMenu.setIconFlag("1");
        manageMenuEntityService.save(menuListMenu);
        menuList.add(menuListMenu);

        for (ManageMenuEntity entity : menuList) {
            ManageRoleMenuEntity manageRoleMenuEntity = new ManageRoleMenuEntity();
            manageRoleMenuEntity.setRoleId(adminRole.getId());
            manageRoleMenuEntity.setMenuId(entity.getId());
            manageRoleMenuEntityService.save(manageRoleMenuEntity);
        }

    }

}
