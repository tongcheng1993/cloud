package com.zifuji.cloud.server.sys.module.dic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.module.dic.cache.DciCache;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.QueryPageDicQo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.GetAllDicDetailVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.QueryPageDicVo;
import org.springframework.stereotype.Service;

import com.zifuji.cloud.server.sys.db.dic.service.DicEntityService;
import com.zifuji.cloud.server.sys.db.dic.service.DicItemEntityService;
import com.zifuji.cloud.server.sys.module.dic.mapper.DicMapper;
import com.zifuji.cloud.server.sys.module.dic.service.DicService;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class DicServiceImpl implements DicService {

    private DicEntityService dicEntityService;

    private DicItemEntityService dicItemEntityService;

    private DicMapper dicMapper;

    private DciCache dciCache;


    @Override
    public List<GetAllDicDetailVo> getAllDicDetail() {
        return dciCache.getAllDicDetail();
    }

    @Override
    public IPage<QueryPageDicVo> queryPageDic(QueryPageDicQo queryPageDicQo) {
        Page<DicEntity> page = new Page<>(queryPageDicQo.getCurrent(), queryPageDicQo.getSize());
        QueryWrapper<DicEntity> dicEntityQueryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(queryPageDicQo.getDicName())) {
            dicEntityQueryWrapper.lambda().like(DicEntity::getDicName, queryPageDicQo.getDicName());
        }
        if (StrUtil.isNotBlank(queryPageDicQo.getDicCode())) {
            dicEntityQueryWrapper.lambda().eq(DicEntity::getDicCode, queryPageDicQo.getDicCode());
        }
        page = dicEntityService.page(page, dicEntityQueryWrapper);

        return page.convert(dicEntity -> {
            QueryPageDicVo vo = new QueryPageDicVo();
            BeanUtil.copyProperties(dicEntity, vo);
            return vo;
        });
    }

    @Override
    public Boolean addDic(AddDicMo addDicMo) {
        QueryWrapper<DicEntity> dicEntityQueryWrapper = new QueryWrapper<>();
        dicEntityQueryWrapper.lambda().eq(DicEntity::getDicCode, addDicMo.getDicCode());
        DicEntity dicEntity = dicEntityService.getOne(dicEntityQueryWrapper);
        if (ObjectUtil.isNotNull(dicEntity)) {
            throw new Exception20000("");
        }
        dicEntity = new DicEntity();
        dicEntity.setDicName(addDicMo.getDicName());
        dicEntity.setDicCode(addDicMo.getDicCode());
        dicEntity.setDicDescription(addDicMo.getDicDescription());
        dicEntityService.save(dicEntity);
        return true;
    }


}
