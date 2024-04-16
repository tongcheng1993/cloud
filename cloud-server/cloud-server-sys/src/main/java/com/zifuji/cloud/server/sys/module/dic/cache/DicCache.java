package com.zifuji.cloud.server.sys.module.dic.cache;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.db.dic.service.DicEntityService;
import com.zifuji.cloud.server.sys.db.dic.service.DicItemEntityService;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicItemVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DicCache {


    private DicEntityService dicEntityService;

    private DicItemEntityService dicItemEntityService;

    @CacheEvict(value = "getAllDicDetail")
    public void delGetAllDicDetailCache() {

    }


    @Cacheable(value = "getAllDicDetail")
    public List<DicVo> getAllDicDetail() {
        List<DicEntity> dicEntityList = dicEntityService.list();
        if (ObjectUtil.isNotEmpty(dicEntityList)) {
            return dicEntityList.stream().map(dicEntity -> {
                DicVo dicVo = new DicVo();
                dicVo.setDicName(dicEntity.getDicName());
                dicVo.setDicCode(dicEntity.getDicCode());
                QueryWrapper<DicItemEntity> dicItemEntityQueryWrapper = new QueryWrapper<>();
                dicItemEntityQueryWrapper.lambda().eq(DicItemEntity::getDicId, dicEntity.getId());
                List<DicItemEntity> dicItemEntityList = dicItemEntityService.list(dicItemEntityQueryWrapper);
                if (ObjectUtil.isNotEmpty(dicItemEntityList)) {
                    dicVo.setDicItemVoList(dicItemEntityList.stream().map(dicItemEntity -> {
                        DicItemVo dicItemVo = new DicItemVo();
                        dicItemVo.setItemCode(dicItemEntity.getItemCode());
                        dicItemVo.setItemValue(dicItemEntity.getItemValue());
                        dicItemVo.setShowFlag(dicItemEntity.getShowFlag());
                        dicItemVo.setCheckFlag(dicItemEntity.getCheckFlag());
                        return dicItemVo;
                    }).collect(Collectors.toList()));
                } else {
                    dicVo.setDicItemVoList(new ArrayList<>());
                }
                return dicVo;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }


}
