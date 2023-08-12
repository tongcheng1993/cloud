package com.zifuji.cloud.server.business.module.relation.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.business.module.relation.bo.FriendComponentMo;
import com.zifuji.cloud.server.business.module.relation.bo.FriendRelationComponentMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendMapper {

	/*
	 * 
	 */
	IPage<FriendComponentMo> selectPageFriend(@Param(value = "page") Page<FriendComponentMo> page,
                                              @Param(Constants.WRAPPER) Wrapper<FriendComponentMo> ew);


	List<FriendRelationComponentMo> getListFriendRelation(Long userId);
}
