package com.zifuji.cloud.server.sys.module.friend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.db.friend.entity.FriendInfoEntity;
import com.zifuji.cloud.server.sys.module.friend.mo.AuditFriendApplyMo;
import com.zifuji.cloud.server.sys.module.friend.mo.MakeFriendApplyMo;
import com.zifuji.cloud.server.sys.module.friend.mo.FriendInfoMo;
import com.zifuji.cloud.server.sys.module.friend.qo.FriendPageQo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendInfoVo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendRelationVo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendVo;

import java.util.List;

public interface FriendService {

    FriendInfoEntity initFriendInfo(Long createById);

    //找朋友列表分页查询交友信息 有查询条件
    IPage<FriendVo> queryPageFriend(FriendPageQo friendPageQo);

    //查看他人账号交友信息
    FriendInfoVo getFriendInfoById(Long userId);

    //保存自己的交友信息
    String saveFriendInfo(FriendInfoMo friendInfoMo);

    //自己查看自己的交友信息
    FriendInfoVo getFriendInfoByMyself();

    String makeFriendApply(MakeFriendApplyMo makeFriendApplyMo);

    String auditFriendApply(AuditFriendApplyMo auditFriendApplyMo);

    List<FriendRelationVo> getListFriendRelation();
}
