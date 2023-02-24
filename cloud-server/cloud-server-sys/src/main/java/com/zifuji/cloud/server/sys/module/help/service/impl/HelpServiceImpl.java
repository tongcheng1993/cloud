package com.zifuji.cloud.server.sys.module.help.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.server.sys.db.help.entity.HelpEntity;
import com.zifuji.cloud.server.sys.db.help.service.HelpEntityService;
import com.zifuji.cloud.server.sys.module.help.mo.HelpContentMo;
import com.zifuji.cloud.server.sys.module.help.service.HelpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class HelpServiceImpl implements HelpService {

    private HelpEntityService helpEntityService;

    @Override
    public String saveHelpContent(HelpContentMo helpContentMo) {
        QueryWrapper<HelpEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HelpEntity::getName, helpContentMo.getName());
        HelpEntity helpEntity = helpEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(helpEntity)) {
            helpEntity = new HelpEntity();
            helpEntity.setName(helpContentMo.getName());
            helpEntity.setContent(helpContentMo.getContent());
            helpEntityService.save(helpEntity);
        } else {
            helpEntity.setContent(helpContentMo.getContent());
            helpEntityService.updateById(helpEntity);
        }
        return helpEntity.getId() + "";
    }

    @Override
    public String getHelpContent() {
        QueryWrapper<HelpEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HelpEntity::getName, "help");
        HelpEntity helpEntity = helpEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(helpEntity)) {
            return "";
        }else{
            return helpEntity.getContent();
        }

    }
}
