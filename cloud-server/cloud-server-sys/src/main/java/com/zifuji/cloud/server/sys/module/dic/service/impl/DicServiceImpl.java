package com.zifuji.cloud.server.sys.module.dic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.base.module.exception.bean.Exception20000;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.module.dic.cache.DicCache;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.ResetDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.ResetDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.DicItemQo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.DicQo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicItemVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicVo;
import org.springframework.stereotype.Service;

import com.zifuji.cloud.server.sys.db.dic.service.DicEntityService;
import com.zifuji.cloud.server.sys.db.dic.service.DicItemEntityService;
import com.zifuji.cloud.server.sys.module.dic.mapper.DicMapper;
import com.zifuji.cloud.server.sys.module.dic.service.DicService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DicServiceImpl implements DicService {

	private DicEntityService dicEntityService;

	private DicItemEntityService dicItemEntityService;

	private DicMapper dicMapper;

	private DicCache dicCache;

	@Override
	public DicVo addDic(AddDicMo addDicMo) {
		QueryWrapper<DicEntity> dicEntityQueryWrapper = new QueryWrapper<>();
		dicEntityQueryWrapper.lambda().eq(DicEntity::getDicCode, addDicMo.getDicCode());
		DicEntity dicEntity = dicEntityService.getOne(dicEntityQueryWrapper);
		if (ObjectUtil.isNotNull(dicEntity)) {
			throw new Exception20000("已经存在" + addDicMo.getDicCode());
		}
		dicEntity = new DicEntity();
		BeanUtil.copyProperties(addDicMo, dicEntity);
		boolean addFlag = dicEntityService.save(dicEntity);
		if (addFlag) {
			flushGetAllDicDetailCache();
			DicVo dicVO = new DicVo();
			BeanUtil.copyProperties(dicEntity, dicVO);
			return dicVO;
		} else {
			throw new Exception20000("新增dic失败");
		}

	}

	@Override
	public Boolean delDic(Long id) {
		flushGetAllDicDetailCache();
		return null;
	}

	@Override
	public DicVo resetDic(ResetDicMo resetDicMo) {
		flushGetAllDicDetailCache();
		return null;
	}

	@Override
	public List<DicVo> queryListDic(DicQo<DicEntity> dicQo) {
		QueryWrapper<DicEntity> dicEntityQueryWrapper = changeDicQuery(dicQo);
		List<DicEntity> list = dicEntityService.list(dicEntityQueryWrapper);
		return list.stream().map(dicEntity -> {
			DicVo vo = new DicVo();
			BeanUtil.copyProperties(dicEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public IPage<DicVo> queryPageDic(DicQo<DicEntity> dicQo) {
		QueryWrapper<DicEntity> dicEntityQueryWrapper = changeDicQuery(dicQo);

		IPage<DicEntity> page = dicEntityService.page(dicQo, dicEntityQueryWrapper);

		return page.convert(dicEntity -> {
			DicVo vo = new DicVo();
			BeanUtil.copyProperties(dicEntity, vo);
			return vo;
		});
	}

	@Override
	public DicVo getDicById(Long id) {
		DicEntity dicEntity = dicEntityService.getById(id);
		if (ObjectUtil.isNotNull(dicEntity)) {
			DicVo vo = new DicVo();
			BeanUtil.copyProperties(dicEntity, vo);
			return vo;
		} else {
			throw new Exception20000("无法找到数据");
		}
	}

	@Override
	public DicItemVo addDicItem(AddDicItemMo addDicItemMo) {

		DicEntity dicEntity = dicEntityService.getById(addDicItemMo.getDicId());
		if (ObjectUtil.isNotNull(dicEntity)) {
			QueryWrapper<DicItemEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(DicItemEntity::getDicId, dicEntity.getTableId()).eq(DicItemEntity::getItemCode,
					addDicItemMo.getItemCode());
			DicItemEntity dicItemEntity = dicItemEntityService.getOne(queryWrapper);
			if (ObjectUtil.isNotNull(dicItemEntity)) {
				throw new Exception20000(dicEntity.getDicCode() + "下" + dicItemEntity.getItemCode() + "已经存在");
			} else {
				dicItemEntity = new DicItemEntity();
				BeanUtil.copyProperties(addDicItemMo, dicItemEntity);
				boolean flag = dicItemEntityService.save(dicItemEntity);
				if (flag) {
					flushGetAllDicDetailCache();
					DicItemVo vo = new DicItemVo();
					BeanUtil.copyProperties(dicItemEntity, vo);
					return vo;
				} else {
					throw new Exception20000("");
				}
			}

		} else {
			throw new Exception20000("");
		}
	}

	@Override
	public Boolean delDicItem(Long id) {
		flushGetAllDicDetailCache();
		return null;
	}

	@Override
	public DicItemVo resetDicItem(ResetDicItemMo resetDicItemMo) {
		flushGetAllDicDetailCache();
		return null;
	}

	@Override
	public List<DicItemVo> queryListDicItem(DicItemQo<DicItemEntity> dicItemQo) {
		QueryWrapper<DicItemEntity> dicItemEntityQueryWrapper = changeDicItemQuery(dicItemQo);

		List<DicItemEntity> list = dicItemEntityService.list(dicItemEntityQueryWrapper);

		return list.stream().map(dicItemEntity -> {
			DicItemVo vo = new DicItemVo();
			BeanUtil.copyProperties(dicItemEntity, vo);
			return vo;
		}).collect(Collectors.toList());

	}

	private QueryWrapper<DicItemEntity> changeDicItemQuery(DicItemQo<DicItemEntity> dicItemQo) {
		QueryWrapper<DicItemEntity> dicItemEntityQueryWrapper = new QueryWrapper<>();
		if (ObjectUtil.isNotNull(dicItemQo.getDicId())) {
			dicItemEntityQueryWrapper.lambda().eq(DicItemEntity::getDicId, dicItemQo.getDicId());
		}
		return dicItemEntityQueryWrapper;
	}

	private QueryWrapper<DicEntity> changeDicQuery(DicQo<DicEntity> dicQo) {
		QueryWrapper<DicEntity> dicEntityQueryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotBlank(dicQo.getDicName())) {
			dicEntityQueryWrapper.lambda().like(DicEntity::getDicName, dicQo.getDicName());
		}
		if (StrUtil.isNotBlank(dicQo.getDicCode())) {
			dicEntityQueryWrapper.lambda().like(DicEntity::getDicCode, dicQo.getDicCode());
		}

		return dicEntityQueryWrapper;
	}

	@Override
	public List<DicVo> getAllDicDetail() {
		return dicCache.getAllDicDetail();
	}

	@Override
	public DicVo getDicByCode(String dicCode) {
		List<DicVo> list = getAllDicDetail();
		if (ObjectUtil.isNotEmpty(list)) {
			for (DicVo dicVo : list) {
				if (StrUtil.equals(dicVo.getDicCode(), dicCode)) {
					return dicVo;
				}
			}
		}
		throw new Exception20000("");
	}

	@Override
	public String getValueByCode(String dicCode, String itemCode) {
		DicVo vo = getDicByCode(dicCode);
		if (ObjectUtil.isNotEmpty(vo.getDicItemVoList())) {
			for (DicItemVo itemVo : vo.getDicItemVoList()) {
				if (StrUtil.equals(itemVo.getItemCode(), itemCode)) {
					return itemVo.getItemValue();
				}
			}
		}
		throw new Exception20000("");
	}

	private void flushGetAllDicDetailCache() {
		dicCache.delGetAllDicDetailCache();
		dicCache.getAllDicDetail();
	}
}
