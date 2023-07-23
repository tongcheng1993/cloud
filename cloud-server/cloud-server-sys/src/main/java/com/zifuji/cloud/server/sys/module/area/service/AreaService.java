package com.zifuji.cloud.server.sys.module.area.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.area.mo.SaveAreaControllerMo;
import com.zifuji.cloud.server.sys.module.area.qo.AreaPageQo;
import com.zifuji.cloud.server.sys.module.area.vo.AreaControllerVo;

import java.util.List;

public interface AreaService {
    //保存地区信息
    String saveArea(SaveAreaControllerMo saveAreaMo);

    IPage<AreaControllerVo> queryPageArea(AreaPageQo areaPageQo);

    List<AreaControllerVo> queryListArea(AreaPageQo areaPageQo);
}
