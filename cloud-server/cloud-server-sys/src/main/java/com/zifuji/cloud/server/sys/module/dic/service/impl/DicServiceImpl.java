package com.zifuji.cloud.server.sys.module.dic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zifuji.cloud.server.sys.module.dic.mo.UpdateDicMo;
import com.zifuji.cloud.server.sys.module.dic.vo.DicVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.db.dic.service.DicEntityService;
import com.zifuji.cloud.server.sys.db.dic.service.DicItemEntityService;
import com.zifuji.cloud.server.sys.module.dic.bo.DicBo;
import com.zifuji.cloud.server.sys.module.dic.bo.DicItemBo;
import com.zifuji.cloud.server.sys.module.dic.mapper.DicMapper;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicMo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicItemPageQo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicPageQo;
import com.zifuji.cloud.server.sys.module.dic.service.DicService;
import com.zifuji.cloud.server.sys.module.dic.vo.DicItemVo;
import com.zifuji.cloud.starter.web.util.MyBatisPlusUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DicServiceImpl implements DicService {

    private DicEntityService dicEntityService;

    private DicItemEntityService dicItemEntityService;

    private DicMapper dicMapper;

    @Override
    public String saveDic(SaveDicMo saveDicMo) {
        QueryWrapper<DicEntity> queryWrapper = new QueryWrapper<DicEntity>();
        queryWrapper.lambda().eq(DicEntity::getCode, saveDicMo.getCode());
        DicEntity dicEntity = dicEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNotNull(dicEntity)) {
            throw new Exception200(saveDicMo.getCode() + "已经存在");
        } else {
            dicEntity = new DicEntity();
            BeanUtil.copyProperties(saveDicMo, dicEntity);
            dicEntityService.save(dicEntity);
        }

        DicItemEntity dicItemEntity = new DicItemEntity();
        dicItemEntity.setDicId(dicEntity.getId());
        dicItemEntity.setCode(saveDicMo.getCode() + "_0");
        dicItemEntity.setValue("未知");
        dicItemEntityService.save(dicItemEntity);

        return dicEntity.getId() + "";
    }

    @Override
    public String updateDic(UpdateDicMo updateDicMo) {
        DicEntity dicEntity = dicEntityService.getById(updateDicMo.getId());
        if (ObjectUtil.isNull(dicEntity)) {
            throw new Exception200("");
        }
        BeanUtil.copyProperties(updateDicMo, dicEntity);
        return dicEntity.getId() + "";
    }

    @Override
    public Boolean delDic(Long id) {
        DicEntity dicEntity = dicEntityService.getById(id);
        if (ObjectUtil.isNull(dicEntity)) {
            return false;
        }
        dicEntityService.removeById(id);
        QueryWrapper<DicItemEntity> queryWrapper = new QueryWrapper<DicItemEntity>();
        queryWrapper.lambda().eq(DicItemEntity::getDicId, id);
        dicItemEntityService.remove(queryWrapper);
        return true;
    }

    @Override
    public DicVo getDicInfoById(Long id) {
        DicVo vo = new DicVo();
        DicEntity dicEntity = dicEntityService.getById(id);
        if (ObjectUtil.isNull(dicEntity)) {
            throw new Exception200("");
        }
        BeanUtil.copyProperties(dicEntity, vo);
        return vo;
    }


    @Override
    public IPage<DicVo> queryPageDic(DicPageQo dicPageQo) {
        IPage<DicBo> page = selectPageDic(dicPageQo);

        return page.convert(dicBo -> {
            DicVo dicVo = new DicVo();
            BeanUtil.copyProperties(dicBo, dicVo);
            return dicVo;
        });
    }

    @Override
    public List<DicVo> initDic() {
        List<DicEntity> dicEntityList = dicEntityService.list();
        List<DicItemEntity> dicItemEntityList = dicItemEntityService.list();
        return dicEntityList.stream().map(dicEntity -> {
            DicVo dicVo = new DicVo();
            BeanUtil.copyProperties(dicEntity, dicVo);
            List<DicItemVo> dicItemVoList = new ArrayList<>();
            for(DicItemEntity dicItemEntity:dicItemEntityList){
                if(dicEntity.getId().equals(dicItemEntity.getDicId())){
                    DicItemVo dicItemVo = new DicItemVo();
                    BeanUtil.copyProperties(dicItemEntity, dicItemVo);
                    dicItemVoList.add(dicItemVo);
                }
            }
            dicVo.setDicItemVoList(dicItemVoList);
            return dicVo;
        }).collect(Collectors.toList());
    }


    @Override
    public String saveDicItem(SaveDicItemMo saveDicItemMo) {
        DicEntity dicEntity = dicEntityService.getById(saveDicItemMo.getDicId());
        if (ObjectUtil.isNull(dicEntity)) {
            throw new Exception200("");
        }
        QueryWrapper<DicItemEntity> queryWrapper = new QueryWrapper<DicItemEntity>();
        queryWrapper.lambda().orderByDesc(DicItemEntity::getCreateTime);
        List<DicItemEntity> list = dicItemEntityService.list(queryWrapper);
        if (ObjectUtil.isEmpty(list)) {
            throw new Exception200("");
        }
        String oldCode = list.get(0).getCode();
        Integer index = oldCode.lastIndexOf("_") + 1;
        Integer next = Integer.valueOf(oldCode.substring(index)) + 1;
        String code = oldCode.substring(0, index) + next;
        DicItemEntity dicItemEntity = new DicItemEntity();
        BeanUtil.copyProperties(saveDicItemMo, dicItemEntity);
        dicItemEntity.setCode(code);
        dicItemEntityService.save(dicItemEntity);
        delCacheDicCode(dicEntity.getCode());
        return dicItemEntity.getId() + "";
    }

    @CacheEvict(value = "queryListDicItemByDicCode", key = "#dicCode")
    public void delCacheDicCode(String dicCode) {

    }

    @Cacheable(value = "queryListDicItemByDicCode", key = "#dicCode")
    @Override
    public List<DicItemVo> queryListDicItemByDicCode(String dicCode) {
        DicItemPageQo dicItemPageQo = new DicItemPageQo();
        dicItemPageQo.setDicCode(dicCode);
        return queryListDicItem(dicItemPageQo);
    }


    @Override
    public List<DicItemVo> queryListDicItem(DicItemPageQo dicItemPageQo) {
        List<DicItemBo> list = selectListDicItem(dicItemPageQo);
        return list.stream().map(dicItemBo -> {
            DicItemVo dicItemVo = new DicItemVo();
            BeanUtil.copyProperties(dicItemBo, dicItemVo);
            return dicItemVo;
        }).collect(Collectors.toList());
    }


    private IPage<DicBo> selectPageDic(DicPageQo dicPageQo) {
        Page<DicBo> page = new Page<DicBo>(dicPageQo.getCurrent(), dicPageQo.getSize());
        QueryWrapper<DicBo> queryWrapper = new QueryWrapper<DicBo>();

        MyBatisPlusUtil.orderWrapper(queryWrapper, dicPageQo.getOrders());
        queryWrapper.groupBy("id");
        return dicMapper.selectPageDic(page, queryWrapper);
    }

    private List<DicItemBo> selectListDicItem(DicItemPageQo dicItemPageQo) {
        QueryWrapper<DicItemBo> queryWrapper = new QueryWrapper<DicItemBo>();
        if (ObjectUtil.isNotNull(dicItemPageQo.getDicId())) {
            queryWrapper.eq("zsd.id", dicItemPageQo.getDicId());
        }
        if (StrUtil.isNotBlank(dicItemPageQo.getDicCode())) {
            queryWrapper.eq("zsd.code", dicItemPageQo.getDicCode());
        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, dicItemPageQo.getOrders());
        queryWrapper.groupBy("id");
        return dicMapper.selectListDicItem(queryWrapper);
    }


}
