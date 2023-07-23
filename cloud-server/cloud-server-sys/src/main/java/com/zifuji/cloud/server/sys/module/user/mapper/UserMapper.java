package com.zifuji.cloud.server.sys.module.user.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zifuji.cloud.server.sys.module.user.bo.WebPermissionComponentMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.user.bo.WebMenuComponentMo;
import com.zifuji.cloud.server.sys.module.user.bo.WebRoleComponentMo;
import com.zifuji.cloud.server.sys.module.user.bo.WebUserComponentMo;

@Mapper
public interface UserMapper {

    List<WebUserComponentMo> selectListUser(@Param(Constants.WRAPPER) Wrapper<WebUserComponentMo> ew);

    IPage<WebUserComponentMo> selectPageUser(@Param(value = "page") Page<WebUserComponentMo> page, @Param(Constants.WRAPPER) Wrapper<WebUserComponentMo> ew);

    List<WebRoleComponentMo> selectListRole(@Param(Constants.WRAPPER) Wrapper<WebRoleComponentMo> ew);

    IPage<WebRoleComponentMo> selectPageRole(@Param(value = "page") Page<WebRoleComponentMo> page, @Param(Constants.WRAPPER) Wrapper<WebRoleComponentMo> ew);

    List<WebPermissionComponentMo> selectListPermission(@Param(Constants.WRAPPER) Wrapper<WebPermissionComponentMo> ew);

    IPage<WebPermissionComponentMo> selectPagePermission(@Param(value = "page") Page<WebPermissionComponentMo> page, @Param(Constants.WRAPPER) Wrapper<WebPermissionComponentMo> ew);

    List<WebMenuComponentMo> selectListMenu(@Param(Constants.WRAPPER) Wrapper<WebMenuComponentMo> ew);

    IPage<WebMenuComponentMo> selectPageMenu(@Param(value = "page") Page<WebMenuComponentMo> page, @Param(Constants.WRAPPER) Wrapper<WebMenuComponentMo> ew);

}
