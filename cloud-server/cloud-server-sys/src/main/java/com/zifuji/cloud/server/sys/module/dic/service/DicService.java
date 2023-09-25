package com.zifuji.cloud.server.sys.module.dic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.QueryPageDicQo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.GetAllDicDetailVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.QueryPageDicVo;

import java.util.List;

public interface DicService {

    List<GetAllDicDetailVo> getAllDicDetail();

    IPage<QueryPageDicVo> queryPageDic(QueryPageDicQo queryPageDicQo);

    Boolean addDic(AddDicMo addDicMo);
}
