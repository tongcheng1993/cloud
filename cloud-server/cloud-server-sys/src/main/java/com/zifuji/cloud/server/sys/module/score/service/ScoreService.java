package com.zifuji.cloud.server.sys.module.score.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.score.qo.ScorePageQo;
import com.zifuji.cloud.server.sys.module.score.vo.ScoreInfoVo;


import java.util.List;

public interface ScoreService {


    Boolean registerScore(Long createById);

    IPage<String> queryPageScore(ScorePageQo scorePageQo);

    List<String> queryListScore(ScorePageQo scorePageQo);

    String getScoreInfoById(Long id);


    ScoreInfoVo getScoreInfoByMyself();
}
