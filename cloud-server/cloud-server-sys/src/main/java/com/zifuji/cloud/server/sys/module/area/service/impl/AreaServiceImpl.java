package com.zifuji.cloud.server.sys.module.area.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.db.area.entity.AreaEntity;
import com.zifuji.cloud.server.sys.module.area.bo.AreaComponentMo;
import com.zifuji.cloud.server.sys.module.area.mapper.AreaMapper;
import com.zifuji.cloud.server.sys.module.area.mo.SaveAreaControllerMo;
import com.zifuji.cloud.server.sys.module.area.qo.AreaPageQo;
import com.zifuji.cloud.server.sys.module.area.vo.AreaControllerVo;

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
	public String saveArea(SaveAreaControllerMo saveAreaMo) {
		QueryWrapper<AreaEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(AreaEntity::getRealName,saveAreaMo.getRealName());
		AreaEntity areaEntity=areaEntityService.getOne(queryWrapper);
		if(ObjectUtil.isNotNull(areaEntity)){
			throw new Exception20000("");
		}
		areaEntity = new AreaEntity();
		BeanUtil.copyProperties(saveAreaMo,areaEntity);
		areaEntityService.save(areaEntity);
		return areaEntity.getId()+"";
	}

	@Override
	public IPage<AreaControllerVo> queryPageArea(AreaPageQo areaPageQo) {
		IPage<AreaComponentMo> page = selectPageArea(areaPageQo);
		return page.convert(areaBo -> {
			AreaControllerVo areaVo = new AreaControllerVo();
			BeanUtil.copyProperties(areaBo, areaVo);
			return areaVo;
		});


	}

	@Override
	public List<AreaControllerVo> queryListArea(AreaPageQo areaPageQo) {
		List<AreaComponentMo> list =selectListArea(areaPageQo);
		return list.stream().map(areaBo ->{
			AreaControllerVo areaVo = new AreaControllerVo();
			BeanUtil.copyProperties(areaBo, areaVo);
			return areaVo;
		}).collect(Collectors.toList());
	}
	
	
	
	private IPage<AreaComponentMo> selectPageArea(AreaPageQo areaPageQo){
		Page<AreaComponentMo> page = new Page<AreaComponentMo>(areaPageQo.getCurrent(),areaPageQo.getSize());
		QueryWrapper<AreaComponentMo> queryWrapper=new QueryWrapper<>();
		
		

		return areaMapper.selectPageArea(page, queryWrapper);
	}
	
	
	private List<AreaComponentMo> selectListArea(AreaPageQo areaPageQo){
		QueryWrapper<AreaComponentMo> queryWrapper=new QueryWrapper<>();
		
		

		return areaMapper.selectListArea( queryWrapper);
	}
}
