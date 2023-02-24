package com.zifuji.cloud.server.sys.module.user.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zifuji.cloud.server.sys.module.user.bo.WebPermissionBo;
import com.zifuji.cloud.server.sys.module.user.qo.WebPermissionPageQo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.user.bo.WebMenuBo;
import com.zifuji.cloud.server.sys.module.user.bo.WebRoleBo;
import com.zifuji.cloud.server.sys.module.user.bo.WebUserBo;

@Mapper
public interface UserMapper {

    List<WebUserBo> selectListUser(@Param(Constants.WRAPPER) Wrapper<WebUserBo> ew);

    IPage<WebUserBo> selectPageUser(@Param(value = "page") Page<WebUserBo> page, @Param(Constants.WRAPPER) Wrapper<WebUserBo> ew);

    List<WebRoleBo> selectListRole(@Param(Constants.WRAPPER) Wrapper<WebRoleBo> ew);

    IPage<WebRoleBo> selectPageRole(@Param(value = "page") Page<WebRoleBo> page, @Param(Constants.WRAPPER) Wrapper<WebRoleBo> ew);

    List<WebPermissionBo> selectListPermission(@Param(Constants.WRAPPER) Wrapper<WebPermissionBo> ew);

    IPage<WebPermissionBo> selectPagePermission(@Param(value = "page") Page<WebPermissionBo> page, @Param(Constants.WRAPPER) Wrapper<WebPermissionBo> ew);

    List<WebMenuBo> selectListMenu(@Param(Constants.WRAPPER) Wrapper<WebMenuBo> ew);

    IPage<WebMenuBo> selectPageMenu(@Param(value = "page") Page<WebMenuBo> page, @Param(Constants.WRAPPER) Wrapper<WebMenuBo> ew);

}
