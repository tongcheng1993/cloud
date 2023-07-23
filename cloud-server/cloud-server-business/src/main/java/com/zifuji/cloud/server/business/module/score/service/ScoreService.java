package com.zifuji.cloud.server.business.module.score.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.db.score.entity.ScoreAccountEntity;
import com.zifuji.cloud.server.business.module.score.qo.ScoreRecordPageQo;
import com.zifuji.cloud.server.business.module.score.vo.ScoreAccountControllerVo;
import com.zifuji.cloud.server.business.module.score.vo.ScoreRecordControllerVo;

public interface ScoreService {

    ScoreAccountEntity initScoreAccount(Long createById);

    ScoreAccountControllerVo getScoreAccountVoById(Long id);

    ScoreAccountControllerVo getScoreAccountVoByMyself();

    IPage<ScoreRecordControllerVo> queryPageScoreRecord(ScoreRecordPageQo scoreRecordPageQo);

}
