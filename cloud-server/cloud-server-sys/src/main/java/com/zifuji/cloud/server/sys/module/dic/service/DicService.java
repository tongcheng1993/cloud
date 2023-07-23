package com.zifuji.cloud.server.sys.module.dic.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicItemControllerMo;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicControllerMo;
import com.zifuji.cloud.server.sys.module.dic.mo.UpdateDicControllerMo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicItemPageQo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicPageQo;
import com.zifuji.cloud.server.sys.module.dic.vo.DicItemControllerVo;
import com.zifuji.cloud.server.sys.module.dic.vo.DicControllerVo;

public interface DicService {

    String saveDic(SaveDicControllerMo saveDicMo);

    String updateDic(UpdateDicControllerMo updateDicMo);

    Boolean delDic(Long id);

    DicControllerVo getDicInfoById(Long id);

    IPage<DicControllerVo> queryPageDic(DicPageQo dicPageQo);

    List<DicControllerVo> initDic();

    String saveDicItem(SaveDicItemControllerMo saveDicItemMo);


    List<DicItemControllerVo> queryListDicItem(DicItemPageQo dicItemPageQo);

    List<DicItemControllerVo> queryListDicItemByDicCode(String dicCode);


}
