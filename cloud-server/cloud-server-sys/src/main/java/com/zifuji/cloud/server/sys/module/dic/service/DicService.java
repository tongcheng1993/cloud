package com.zifuji.cloud.server.sys.module.dic.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicMo;
import com.zifuji.cloud.server.sys.module.dic.mo.UpdateDicMo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicItemPageQo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicPageQo;
import com.zifuji.cloud.server.sys.module.dic.vo.DicItemVo;
import com.zifuji.cloud.server.sys.module.dic.vo.DicVo;

public interface DicService {

    String saveDic(SaveDicMo saveDicMo);

    String updateDic(UpdateDicMo updateDicMo);

    Boolean delDic(Long id);

    DicVo getDicInfoById(Long id);

    IPage<DicVo> queryPageDic(DicPageQo dicPageQo);

    List<DicVo> initDic();

    String saveDicItem(SaveDicItemMo saveDicItemMo);


    List<DicItemVo> queryListDicItem(DicItemPageQo dicItemPageQo);

    List<DicItemVo> queryListDicItemByDicCode(String dicCode);


}
