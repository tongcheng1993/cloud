package com.zifuji.cloud.server.business.module.score.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.db.score.entity.ScoreAccountEntity;
import com.zifuji.cloud.server.business.db.score.service.ScoreAccountEntityService;
import com.zifuji.cloud.server.business.db.score.service.ScoreRecordEntityService;
import com.zifuji.cloud.server.business.module.score.mapper.ScoreMapper;
import com.zifuji.cloud.server.business.module.score.qo.ScoreRecordPageQo;
import com.zifuji.cloud.server.business.module.score.service.ScoreService;
import com.zifuji.cloud.server.business.module.score.vo.ScoreAccountVo;
import com.zifuji.cloud.server.business.module.score.vo.ScoreRecordVo;
import com.zifuji.cloud.server.base.object.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private ScoreMapper scoreMapper;

    private ScoreAccountEntityService scoreAccountEntityService;

    private ScoreRecordEntityService scoreRecordEntityService;


    @Override
    public ScoreAccountEntity initScoreAccount(Long createById) {
        QueryWrapper<ScoreAccountEntity> queryWrapper = new QueryWrapper<ScoreAccountEntity>();
        queryWrapper.lambda().eq(ScoreAccountEntity::getCreateBy, createById);
        ScoreAccountEntity scoreAccountEntity = scoreAccountEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(scoreAccountEntity)) {
            Integer i = 0;
            scoreAccountEntity = new ScoreAccountEntity();
            scoreAccountEntity.setCreateBy(createById);
            scoreAccountEntity.setScoreNum(i);
            scoreAccountEntityService.save(scoreAccountEntity);
        }
        return scoreAccountEntity;
    }

    @Override
    public ScoreAccountVo getScoreAccountVoById(Long id) {
        ScoreAccountVo vo = new ScoreAccountVo();
        QueryWrapper<ScoreAccountEntity> queryWrapper = new QueryWrapper<ScoreAccountEntity>();
        queryWrapper.lambda().eq(ScoreAccountEntity::getCreateBy, id);
        ScoreAccountEntity scoreAccountEntity = scoreAccountEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(scoreAccountEntity)) {
            scoreAccountEntity = initScoreAccount(id);
        }
        BeanUtil.copyProperties(scoreAccountEntity,vo);
        return vo;
    }

    @Override
    public ScoreAccountVo getScoreAccountVoByMyself() {
        Long userId = SecurityUtil.getUserDetails().getId();
        return getScoreAccountVoById(userId);
    }

    @Override
    public IPage<ScoreRecordVo> queryPageScoreRecord(ScoreRecordPageQo scoreRecordPageQo) {
        return null;
    }


}
