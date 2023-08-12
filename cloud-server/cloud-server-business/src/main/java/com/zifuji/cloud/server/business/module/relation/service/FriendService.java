package com.zifuji.cloud.server.business.module.relation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.base.feign.sys.vo.DownloadFileVo;
import com.zifuji.cloud.server.business.db.friend.entity.FriendInfoEntity;
import com.zifuji.cloud.server.business.module.relation.mo.AuditFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.relation.mo.MakeFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.relation.mo.FriendInfoControllerMo;
import com.zifuji.cloud.server.business.module.relation.qo.FriendPageQo;
import com.zifuji.cloud.server.business.module.relation.vo.FriendInfoControllerVo;
import com.zifuji.cloud.server.business.module.relation.vo.FriendRelationControllerVo;
import com.zifuji.cloud.server.business.module.relation.vo.FriendControllerVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FriendService {

    FriendInfoEntity initFriendInfo(Long createById);

    // 找朋友列表分页查询交友信息 有查询条件
    IPage<FriendControllerVo> queryPageFriend(FriendPageQo friendPageQo);

    // 查看他人账号交友信息
    FriendInfoControllerVo getFriendInfoById(Long userId);

    // 上传交友信息的照片
    String uploadFriendInfoImgFile(MultipartFile file);

    DownloadFileVo downloadFriendInfoImgFile(Long id) throws IOException;

    // 保存自己的交友信息
    String saveFriendInfo(FriendInfoControllerMo friendInfoMo);

    // 自己查看自己的交友信息
    FriendInfoControllerVo getFriendInfoByMyself();

    String makeFriendApply(MakeFriendApplyControllerMo makeFriendApplyMo);

    String auditFriendApply(AuditFriendApplyControllerMo auditFriendApplyMo);

    List<FriendRelationControllerVo> getListFriendRelation();
}
