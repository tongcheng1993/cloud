package com.zifuji.cloud.server.sys.module.area.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.db.area.entity.AreaEntity;
import com.zifuji.cloud.server.sys.module.area.controller.qo.AreaQo;
import com.zifuji.cloud.server.sys.module.area.controller.vo.AreaVo;

import java.util.List;

public interface AreaService {

	List<AreaVo> queryListArea(AreaQo areaQo);

	IPage<AreaVo> queryPageArea(AreaQo areaQo);
}
