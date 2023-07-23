package com.zifuji.cloud.server.sys.module.manageUser.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageMenuComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManagePermissionComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageRoleComponentMo;
import com.zifuji.cloud.server.sys.module.manageUser.bo.ManageUserComponentMo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManageUserMapper {

    List<ManageUserComponentMo> selectListUser(@Param(Constants.WRAPPER) Wrapper<ManageUserComponentMo> ew);

    IPage<ManageUserComponentMo> selectPageUser(@Param(value = "page") Page<ManageUserComponentMo> page, @Param(Constants.WRAPPER) Wrapper<ManageUserComponentMo> ew);

    List<ManageRoleComponentMo> selectListRole(@Param(Constants.WRAPPER) Wrapper<ManageRoleComponentMo> ew);

    IPage<ManageRoleComponentMo> selectPageRole(@Param(value = "page") Page<ManageRoleComponentMo> page, @Param(Constants.WRAPPER) Wrapper<ManageRoleComponentMo> ew);

    List<ManagePermissionComponentMo> selectListPermission(@Param(Constants.WRAPPER) Wrapper<ManagePermissionComponentMo> ew);

    IPage<ManagePermissionComponentMo> selectPagePermission(@Param(value = "page") Page<ManagePermissionComponentMo> page, @Param(Constants.WRAPPER) Wrapper<ManagePermissionComponentMo> ew);

    List<ManageMenuComponentMo> selectListMenu(@Param(Constants.WRAPPER) Wrapper<ManageMenuComponentMo> ew);

    IPage<ManageMenuComponentMo> selectPageMenu(@Param(value = "page") Page<ManageMenuComponentMo> page, @Param(Constants.WRAPPER) Wrapper<ManageMenuComponentMo> ew);
}
