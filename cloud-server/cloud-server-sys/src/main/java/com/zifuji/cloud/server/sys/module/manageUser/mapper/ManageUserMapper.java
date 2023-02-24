package com.zifuji.cloud.server.sys.module.manageUser.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageMenuBo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManagePermissionBo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageRoleBo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageUserBo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManageUserMapper {

    List<ManageUserBo> selectListUser(@Param(Constants.WRAPPER) Wrapper<ManageUserBo> ew);

    IPage<ManageUserBo> selectPageUser(@Param(value = "page") Page<ManageUserBo> page, @Param(Constants.WRAPPER) Wrapper<ManageUserBo> ew);

    List<ManageRoleBo> selectListRole(@Param(Constants.WRAPPER) Wrapper<ManageRoleBo> ew);

    IPage<ManageRoleBo> selectPageRole(@Param(value = "page") Page<ManageRoleBo> page, @Param(Constants.WRAPPER) Wrapper<ManageRoleBo> ew);

    List<ManagePermissionBo> selectListPermission(@Param(Constants.WRAPPER) Wrapper<ManagePermissionBo> ew);

    IPage<ManagePermissionBo> selectPagePermission(@Param(value = "page") Page<ManagePermissionBo> page, @Param(Constants.WRAPPER) Wrapper<ManagePermissionBo> ew);

    List<ManageMenuBo> selectListMenu(@Param(Constants.WRAPPER) Wrapper<ManageMenuBo> ew);

    IPage<ManageMenuBo> selectPageMenu(@Param(value = "page") Page<ManageMenuBo> page, @Param(Constants.WRAPPER) Wrapper<ManageMenuBo> ew);
}
