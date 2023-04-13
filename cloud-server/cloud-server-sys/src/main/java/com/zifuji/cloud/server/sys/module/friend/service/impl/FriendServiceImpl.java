package com.zifuji.cloud.server.sys.module.friend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.base.feign.websocket.WebsocketFeignClient;
import com.zifuji.cloud.server.sys.db.friend.entity.FriendApplyEntity;
import com.zifuji.cloud.server.sys.db.friend.entity.FriendInfoEntity;
import com.zifuji.cloud.server.sys.db.friend.entity.FriendRelationEntity;
import com.zifuji.cloud.server.sys.db.friend.service.FriendApplyEntityService;
import com.zifuji.cloud.server.sys.db.friend.service.FriendInfoEntityService;
import com.zifuji.cloud.server.sys.db.friend.service.FriendRelationEntityService;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.friend.bo.FriendBo;
import com.zifuji.cloud.server.sys.module.friend.mapper.FriendMapper;
import com.zifuji.cloud.server.sys.module.friend.mo.AuditFriendApplyMo;
import com.zifuji.cloud.server.sys.module.friend.mo.MakeFriendApplyMo;
import com.zifuji.cloud.server.sys.module.friend.mo.FriendInfoMo;
import com.zifuji.cloud.server.sys.module.friend.qo.FriendPageQo;
import com.zifuji.cloud.server.sys.module.friend.service.FriendService;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendInfoVo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendRelationVo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendVo;
import com.zifuji.cloud.server.base.object.SecurityUtil;
import com.zifuji.cloud.server.base.util.MyBatisPlusUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FriendServiceImpl implements FriendService {

    private WebsocketFeignClient websocketFeignClient;

    private FriendInfoEntityService friendInfoEntityService;

    private FriendApplyEntityService friendApplyEntityService;

    private FriendRelationEntityService friendRelationEntityService;

    private FriendMapper friendMapper;

    private FileService fileService;


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
    public String saveFriendInfo(FriendInfoMo friendInfoMo) {
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
    public FriendInfoVo getFriendInfoByMyself() {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        FriendInfoEntity friendInfoEntity = getFriendInfoByCreateBy(userInfo.getId());
        FriendInfoVo friendInfoVo = new FriendInfoVo();
        BeanUtil.copyProperties(friendInfoEntity, friendInfoVo);
        return friendInfoVo;

    }
    private FriendInfoEntity getFriendInfoByCreateBy(Long id){
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, id);
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if(ObjectUtil.isNull(friendInfoEntity)){
            friendInfoEntity =   initFriendInfo(id);
        }
        return friendInfoEntity;
    }

    @Override
    public FriendInfoVo getFriendInfoById(Long friendId) {
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getId, friendId);
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        FriendInfoVo friendInfoVo = new FriendInfoVo();
        BeanUtil.copyProperties(friendInfoEntity, friendInfoVo);
        return friendInfoVo;
    }

    @Override
    public String uploadFriendInfoImgFile(MultipartFile file) {
        return fileService.uploadFile("/friend/uploadFriendInfoImgFile",file);
    }


    @Override
    public IPage<FriendVo> queryPageFriend(FriendPageQo friendPageQo) {
        return selectPageFriend(friendPageQo).convert(friendInfoBo -> {
            FriendVo friendVo = new FriendVo();
            BeanUtil.copyProperties(friendInfoBo, friendVo);
            return friendVo;
        });
    }

    @Override
    public String makeFriendApply(MakeFriendApplyMo makeFriendApplyMo) {
        UserInfo userInfo = SecurityUtil.getUserDetails();
        QueryWrapper<FriendInfoEntity> queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, userInfo.getId());
        FriendInfoEntity myFriendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(myFriendInfoEntity)) {
            throw new Exception200("请完善自身交友信息");
        }
        queryWrapper = new QueryWrapper<FriendInfoEntity>();
        queryWrapper.lambda().eq(FriendInfoEntity::getCreateBy, makeFriendApplyMo.getToBy());
        FriendInfoEntity friendInfoEntity = friendInfoEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(friendInfoEntity)) {
            throw new Exception200("数据错误");
        }


        FriendApplyEntity friendApplyEntity = new FriendApplyEntity();
        BeanUtil.copyProperties(makeFriendApplyMo, friendApplyEntity);
        friendApplyEntity.setAuditFlag(true);
        friendApplyEntity.setShowFlag(true);
        friendApplyEntityService.save(friendApplyEntity);

        return friendApplyEntity.getId() + "";
    }

    @Override
    public String auditFriendApply(AuditFriendApplyMo auditFriendApplyMo) {
        Long id = SecurityUtil.getUserDetails().getId();

        FriendApplyEntity friendApplyEntity = friendApplyEntityService.getById(auditFriendApplyMo.getId());
        if (ObjectUtil.isNull(friendApplyEntity)) {
            throw new Exception200("数据错误");
        }
        if (!friendApplyEntity.getToBy().equals(id)) {
            throw new Exception200("数据错误");
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
    public List<FriendRelationVo> getListFriendRelation() {
        return null;
    }


    private IPage<FriendBo> selectPageFriend(FriendPageQo friendPageQo) {
        Page<FriendBo> page = new Page<FriendBo>(friendPageQo.getCurrent(), friendPageQo.getSize());
        QueryWrapper<FriendBo> queryWrapper = new QueryWrapper<FriendBo>();
        if (StrUtil.isNotBlank(friendPageQo.getSex())) {
            queryWrapper.eq("zbfi.sex", friendPageQo.getSex());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, friendPageQo.getOrders());
        return friendMapper.selectPageFriend(page, queryWrapper);
    }

}
