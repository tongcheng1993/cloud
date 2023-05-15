package com.zifuji.cloud.server.business.module.friend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.business.module.friend.bo.FriendBo;
import com.zifuji.cloud.server.business.module.friend.bo.FriendRelationBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendMapper {

	/*
	 * 
	 */
	IPage<FriendBo> selectPageFriend(@Param(value = "page") Page<FriendBo> page,
                                     @Param(Constants.WRAPPER) Wrapper<FriendBo> ew);


	List<FriendRelationBo> getListFriendRelation(Long userId);
}
