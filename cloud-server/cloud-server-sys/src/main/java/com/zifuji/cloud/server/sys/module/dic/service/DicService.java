package com.zifuji.cloud.server.sys.module.dic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.UpdateDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.UpdateDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.DicItemQo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.DicQo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicItemVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicVo;

import java.util.List;

public interface DicService {

	DicVo addDic(AddDicMo addDicMo);

	Boolean delDic(Long id);

	DicVo updateDic(UpdateDicMo updateDicMo);

	List<DicVo> queryListDic(DicQo dicQo);

	IPage<DicVo> queryPageDic(DicQo dicQo);

	DicVo getDicById(Long id);

	DicItemVo addDicItem(AddDicItemMo addDicItemMo);

	Boolean delDicItem(Long id);

	DicItemVo updateDicItem(UpdateDicItemMo updateDicItemMo);

	List<DicItemVo> queryListDicItem(DicItemQo dicItemQo);

	List<DicVo> getAllDicDetail();

	DicVo getDicByCode(String dicCode);

	String getValueByCode(String dicCode, String itemCode);
}
