package com.zifuji.cloud.server.base.object;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.component.BaseComponentMo;
import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;

public interface BaseService {

    <T extends BaseControllerMo> String save(T mo);

    <T extends BaseControllerMo> String add(T mo);

    <T extends BaseControllerMo> String update(T mo);

    Boolean del(Long id);

    <K extends BaseControllerPageQo, V extends BaseComponentMo> IPage<V> queryPageBo(K qo);
}
