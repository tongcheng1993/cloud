package com.zifuji.cloud.server.sys.module.score.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.db.score.entity.ScoreAccountEntity;
import com.zifuji.cloud.server.sys.module.score.qo.ScoreRecordPageQo;
import com.zifuji.cloud.server.sys.module.score.vo.ScoreAccountVo;
import com.zifuji.cloud.server.sys.module.score.vo.ScoreRecordVo;

public interface ScoreService {

    ScoreAccountEntity initScoreAccount(Long createById);

    ScoreAccountVo getScoreAccountVoById(Long id);

    ScoreAccountVo getScoreAccountVoByMyself();

    IPage<ScoreRecordVo> queryPageScoreRecord(ScoreRecordPageQo scoreRecordPageQo);

}
