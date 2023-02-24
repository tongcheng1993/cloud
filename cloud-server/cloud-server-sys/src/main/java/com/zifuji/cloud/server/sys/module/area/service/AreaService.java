package com.zifuji.cloud.server.sys.module.area.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.area.mo.SaveAreaMo;
import com.zifuji.cloud.server.sys.module.area.qo.AreaPageQo;
import com.zifuji.cloud.server.sys.module.area.vo.AreaVo;

import java.util.List;

public interface AreaService {
    //保存地区信息
    String saveArea(SaveAreaMo saveAreaMo);

    IPage<AreaVo> queryPageArea(AreaPageQo areaPageQo);

    List<AreaVo> queryListArea(AreaPageQo areaPageQo);
}
