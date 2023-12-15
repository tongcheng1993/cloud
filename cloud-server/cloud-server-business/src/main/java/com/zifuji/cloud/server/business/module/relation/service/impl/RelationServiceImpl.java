package com.zifuji.cloud.server.business.module.relation.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.base.feign.sys.FileFeignClient;
import com.zifuji.cloud.server.base.feign.sys.vo.DownloadFileVo;
import com.zifuji.cloud.server.base.feign.websocket.WebsocketFeignClient;
import com.zifuji.cloud.server.business.db.friend.entity.FriendApplyEntity;
import com.zifuji.cloud.server.business.db.friend.entity.FriendInfoEntity;
import com.zifuji.cloud.server.business.db.friend.entity.FriendRelationEntity;
import com.zifuji.cloud.server.business.db.friend.service.FriendApplyEntityService;
import com.zifuji.cloud.server.business.db.friend.service.FriendInfoEntityService;
import com.zifuji.cloud.server.business.db.friend.service.FriendRelationEntityService;
import com.zifuji.cloud.server.business.module.relation.mapper.RelationMapper;
import com.zifuji.cloud.server.business.module.relation.controller.mo.AuditFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.relation.controller.mo.MakeFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.relation.controller.mo.FriendInfoControllerMo;
import com.zifuji.cloud.server.business.module.relation.service.RelationService;
import com.zifuji.cloud.server.business.module.relation.controller.vo.FriendInfoControllerVo;
import com.zifuji.cloud.server.business.module.relation.controller.vo.FriendRelationControllerVo;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RelationServiceImpl implements RelationService {

    private WebsocketFeignClient websocketFeignClient;

    private FriendInfoEntityService friendInfoEntityService;

    private FriendApplyEntityService friendApplyEntityService;

    private FriendRelationEntityService friendRelationEntityService;

    private RelationMapper relationMapper;

    private FileFeignClient fileFeignClient;

    @Override
    public FriendInfoEntity initFriendInfo(Long createById) {
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, createById);
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(friendInfoEntity)) {
            friendInfoEntity = new FriendInfoEntity();
            friendInfoEntity.setCreateBy(createById);
            friendInfoEntityService.save(friendInfoEntity);
        }
        return friendInfoEntity;
    }

    @Override
    public String saveFriendInfo(FriendInfoControllerMo friendInfoMo) {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, userInfo.getId());
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(friendInfoEntity)) {
            friendInfoEntity = new FriendInfoEntity();
            BeanUtil.copyProperties(friendInfoMo, friendInfoEntity);
            friendInfoEntityService.save(friendInfoEntity);
        } else {
            BeanUtil.copyProperties(friendInfoMo, friendInfoEntity);
            friendInfoEntityService.updateById(friendInfoEntity);
        }
        return friendInfoEntity.getId() + "";
    }


    @Override
    public FriendInfoControllerVo getFriendInfoByMyself() {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        FriendInfoEntity friendInfoEntity = getFriendInfoByCreateBy(userInfo.getId());
        FriendInfoControllerVo friendInfoVo = new FriendInfoControllerVo();
        BeanUtil.copyProperties(friendInfoEntity, friendInfoVo);
        return friendInfoVo;

    }

    private FriendInfoEntity getFriendInfoByCreateBy(Long id) {
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, id);
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(friendInfoEntity)) {
            friendInfoEntity = initFriendInfo(id);
        }
        return friendInfoEntity;
    }

    @Override
    public FriendInfoControllerVo getFriendInfoById(String friendId) {
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getId, friendId);
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        FriendInfoControllerVo friendInfoVo = new FriendInfoControllerVo();
        BeanUtil.copyProperties(friendInfoEntity, friendInfoVo);
        return friendInfoVo;
    }

    @Override
    public String uploadFriendInfoImgFile(MultipartFile file) {
        return fileFeignClient.uploadFile(file).getResult();
    }

    @Override
    public DownloadFileVo downloadFriendInfoImgFile(String id) throws IOException {
        return fileFeignClient.downloadFile(id).getResult();
    }



    @Override
    public String makeFriendApply(MakeFriendApplyControllerMo makeFriendApplyMo) {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, userInfo.getId());
        FriendInfoEntity myFriendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(myFriendInfoEntity)) {
            throw new Exception20000("请完善自身交友信息");
        }
        queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, makeFriendApplyMo.getToBy());
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(friendInfoEntity)) {
            throw new Exception20000("数据错误");
        }


        FriendApplyEntity friendApplyEntity = new FriendApplyEntity();
        BeanUtil.copyProperties(makeFriendApplyMo, friendApplyEntity);
        friendApplyEntity.setAuditFlag(true);
        friendApplyEntity.setShowFlag(true);
        friendApplyEntityService.save(friendApplyEntity);

        return friendApplyEntity.getId() + "";
    }

    @Override
    public String auditFriendApply(AuditFriendApplyControllerMo auditFriendApplyMo) {
        Long id = SecurityUtil.getUserDetails().getId();

        FriendApplyEntity friendApplyEntity = friendApplyEntityService.getById(auditFriendApplyMo.getId());
        if (ObjectUtil.isNull(friendApplyEntity)) {
            throw new Exception20000("数据错误");
        }
        if (!friendApplyEntity.getToBy().equals(id)) {
            throw new Exception20000("数据错误");
        }
        if (StrUtil.equals(auditFriendApplyMo.getAuditStatus(), BaseConstant.CODE_PASS_BACK_TYPE_1)) {
            FriendRelationEntity friendRelationEntity = new FriendRelationEntity();
            friendRelationEntity.setFromBy(friendApplyEntity.getCreateBy());
            friendRelationEntity.setToBy(friendApplyEntity.getToBy());
            friendRelationEntity.setGroupName(friendApplyEntity.getGroupName());
            friendRelationEntity.setNoteName(friendApplyEntity.getNoteName());
            friendRelationEntityService.save(friendRelationEntity);

            FriendRelationEntity myRelation = new FriendRelationEntity();
            myRelation.setFromBy(id);
            myRelation.setToBy(friendApplyEntity.getCreateBy());
            myRelation.setGroupName(auditFriendApplyMo.getGroupName());
            myRelation.setNoteName(auditFriendApplyMo.getNoteName());
            friendRelationEntityService.save(myRelation);
        } else {

        }
        friendApplyEntity.setAuditFlag(false);
        friendApplyEntity.setAuditStatus(auditFriendApplyMo.getAuditStatus());
        friendApplyEntityService.updateById(friendApplyEntity);

        return auditFriendApplyMo.getId() + "";
    }

    @Override
    public List<FriendRelationControllerVo> getListFriendRelation() {
        return null;
    }




}
