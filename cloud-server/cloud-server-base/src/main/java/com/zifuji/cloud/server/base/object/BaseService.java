package com.zifuji.cloud.server.base.object;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.BaseBo;
import com.zifuji.cloud.base.bean.BaseMo;
import com.zifuji.cloud.base.bean.BasePageQo;

public interface BaseService {

    <T extends BaseMo> String save(T mo);

    <T extends BaseMo> String add(T mo);

    <T extends BaseMo> String update(T mo);

    Boolean del(Long id);

    <K extends BasePageQo, V extends BaseBo> IPage<V> queryPageBo(K qo);
}
