package com.zifuji.cloud.server.sys.module.dic.cache;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.db.dic.service.DicEntityService;
import com.zifuji.cloud.server.sys.db.dic.service.DicItemEntityService;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.GetAllDicDetailChildrenVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.GetAllDicDetailVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DciCache {


    private DicEntityService dicEntityService;

    private DicItemEntityService dicItemEntityService;

    @CacheEvict(value = "getAllDicDetail")
    public void delGetAllDicDetailCache() {

    }


    @Cacheable(value = "getAllDicDetail")
    public List<GetAllDicDetailVo> getAllDicDetail() {
        List<DicEntity> dicEntityList = dicEntityService.list();
        if (ObjectUtil.isNotEmpty(dicEntityList)) {
            return dicEntityList.stream().map(dicEntity -> {
                GetAllDicDetailVo getAllDicDetailVo = new GetAllDicDetailVo();
                getAllDicDetailVo.setDicName(dicEntity.getDicName());
                getAllDicDetailVo.setDicCode(dicEntity.getDicCode());
                QueryWrapper<DicItemEntity> dicItemEntityQueryWrapper = new QueryWrapper<>();
                dicItemEntityQueryWrapper.lambda().eq(DicItemEntity::getDicId, dicEntity.getId());
                List<DicItemEntity> dicItemEntityList = dicItemEntityService.list(dicItemEntityQueryWrapper);
                if (ObjectUtil.isNotEmpty(dicItemEntityList)) {
                    getAllDicDetailVo.setChildren(dicItemEntityList.stream().map(dicItemEntity -> {
                        GetAllDicDetailChildrenVo getAllDicDetailChildrenVo = new GetAllDicDetailChildrenVo();
                        getAllDicDetailChildrenVo.setItemCode(dicItemEntity.getItemCode());
                        getAllDicDetailChildrenVo.setItemValue(dicItemEntity.getItemValue());
                        getAllDicDetailChildrenVo.setShowFlag(dicItemEntity.getShowFlag());
                        getAllDicDetailChildrenVo.setCheckFlag(dicItemEntity.getCheckFlag());
                        return getAllDicDetailChildrenVo;
                    }).collect(Collectors.toList()));
                } else {
                    getAllDicDetailVo.setChildren(new ArrayList<>());
                }
                return getAllDicDetailVo;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }


}
