package com.zifuji.cloud.server.sys.module.area.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.db.area.entity.AreaEntity;
import com.zifuji.cloud.server.sys.module.area.controller.qo.AreaQo;
import com.zifuji.cloud.server.sys.module.area.controller.vo.AreaVo;
import com.zifuji.cloud.server.sys.module.area.mapper.AreaMapper;
import org.springframework.stereotype.Service;

import com.zifuji.cloud.server.sys.db.area.service.AreaEntityService;
import com.zifuji.cloud.server.sys.module.area.service.AreaService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService {

	private AreaEntityService areaEntityService;

	private AreaMapper areaMapper;

	@Override
	public List<AreaVo> queryListArea(AreaQo areaQo) {

		QueryWrapper<AreaEntity> areaEntityQueryWrapper = new QueryWrapper<>();
		if (ObjectUtil.isNotNull(areaQo.getParentIdEq())) {
			areaEntityQueryWrapper.lambda().eq(AreaEntity::getParentId, areaQo.getParentIdEq());
		}
		List<AreaEntity> list = areaEntityService.list(areaEntityQueryWrapper);

		return list.stream().map(areaEntity -> {
			AreaVo vo = new AreaVo();

			BeanUtil.copyProperties(areaEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public IPage<AreaVo> queryPageArea(AreaQo areaQo) {
		// TODO Auto-generated method stub
		return null;
	}

}
