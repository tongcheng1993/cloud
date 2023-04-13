package com.zifuji.cloud.server.sys.module.area.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.area.entity.AreaEntity;
import com.zifuji.cloud.server.sys.module.area.bo.AreaBo;
import com.zifuji.cloud.server.sys.module.area.mapper.AreaMapper;
import com.zifuji.cloud.server.sys.module.area.mo.SaveAreaMo;
import com.zifuji.cloud.server.sys.module.area.qo.AreaPageQo;
import com.zifuji.cloud.server.sys.module.area.vo.AreaVo;
import com.zifuji.cloud.server.base.util.MyBatisPlusUtil;

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
public class AreaServiceImpl implements AreaService{

	
	private AreaEntityService areaEntityService;

	private AreaMapper areaMapper;

	//保存地区
	@Override
	public String saveArea(SaveAreaMo saveAreaMo) {
		QueryWrapper<AreaEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(AreaEntity::getRealName,saveAreaMo.getRealName());
		AreaEntity areaEntity=areaEntityService.getOne(queryWrapper);
		if(ObjectUtil.isNotNull(areaEntity)){
			throw new Exception200("");
		}
		areaEntity = new AreaEntity();
		BeanUtil.copyProperties(saveAreaMo,areaEntity);
		areaEntityService.save(areaEntity);
		return areaEntity.getId()+"";
	}

	@Override
	public IPage<AreaVo> queryPageArea(AreaPageQo areaPageQo) {
		IPage<AreaBo> page = selectPageArea(areaPageQo);
		return page.convert(areaBo -> {
			AreaVo areaVo = new AreaVo();
			BeanUtil.copyProperties(areaBo, areaVo);
			return areaVo;
		});


	}

	@Override
	public List<AreaVo> queryListArea(AreaPageQo areaPageQo) {
		List<AreaBo> list =selectListArea(areaPageQo);
		return list.stream().map(areaBo ->{
			AreaVo areaVo = new AreaVo();
			BeanUtil.copyProperties(areaBo, areaVo);
			return areaVo;
		}).collect(Collectors.toList());
	}
	
	
	
	private IPage<AreaBo> selectPageArea(AreaPageQo areaPageQo){
		Page<AreaBo> page = new Page<AreaBo>(areaPageQo.getCurrent(),areaPageQo.getSize());
		QueryWrapper<AreaBo> queryWrapper=new QueryWrapper<>();
		
		
		MyBatisPlusUtil.orderWrapper(queryWrapper, areaPageQo.getOrders());
		return areaMapper.selectPageArea(page, queryWrapper);
	}
	
	
	private List<AreaBo> selectListArea(AreaPageQo areaPageQo){
		QueryWrapper<AreaBo> queryWrapper=new QueryWrapper<>();
		
		
		MyBatisPlusUtil.orderWrapper(queryWrapper, areaPageQo.getOrders());
		return areaMapper.selectListArea( queryWrapper);
	}
}
