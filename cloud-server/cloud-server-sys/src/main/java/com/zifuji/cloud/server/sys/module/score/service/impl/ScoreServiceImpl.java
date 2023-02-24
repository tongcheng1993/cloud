package com.zifuji.cloud.server.sys.module.score.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.zifuji.cloud.server.sys.db.score.entity.ScoreAccountEntity;
import com.zifuji.cloud.server.sys.db.score.entity.ScoreRecordEntity;
import com.zifuji.cloud.server.sys.db.score.service.ScoreAccountEntityService;
import com.zifuji.cloud.server.sys.db.score.service.ScoreRecordEntityService;
import com.zifuji.cloud.server.sys.module.score.mapper.ScoreMapper;
import com.zifuji.cloud.server.sys.module.score.qo.ScorePageQo;
import com.zifuji.cloud.server.sys.module.score.service.ScoreService;
import com.zifuji.cloud.server.sys.module.score.vo.ScoreInfoVo;
import com.zifuji.cloud.starter.web.object.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private ScoreMapper scoreMapper;

    private ScoreAccountEntityService scoreAccountEntityService;

    private ScoreRecordEntityService scoreRecordEntityService;


    @Override
    public Boolean registerScore(Long createById) {
        QueryWrapper<ScoreAccountEntity> queryWrapper = new QueryWrapper<ScoreAccountEntity>();
        queryWrapper.lambda().eq(ScoreAccountEntity::getCreateBy, createById);
        ScoreAccountEntity scoreAccountEntity = scoreAccountEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(scoreAccountEntity)) {
            Integer i = 100;
            scoreAccountEntity = new ScoreAccountEntity();
            scoreAccountEntity.setCreateBy(createById);
            scoreAccountEntity.setScoreNum(i);
            scoreAccountEntityService.save(scoreAccountEntity);
            ScoreRecordEntity scoreRecordEntity = new ScoreRecordEntity();
            scoreRecordEntity.setCreateBy(createById);
            scoreRecordEntity.setScoreAccountId(scoreAccountEntity.getId());
            scoreRecordEntity.setUpdateNum(i);
            return scoreRecordEntityService.save(scoreRecordEntity);
        } else {
            return true;
        }
    }

    @Override
    public IPage<String> queryPageScore(ScorePageQo scorePageQo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> queryListScore(ScorePageQo scorePageQo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getScoreInfoById(Long id) {

        return null;
    }

    @Override
    public ScoreInfoVo getScoreInfoByMyself() {
        Long userId = SecurityUtil.getUserDetails().getId();
        QueryWrapper<ScoreAccountEntity> queryWrapper = new QueryWrapper<ScoreAccountEntity>();
        queryWrapper.lambda().eq(ScoreAccountEntity::getCreateBy, userId);
        ScoreAccountEntity scoreAccountEntity = scoreAccountEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(scoreAccountEntity)) {

        } else {

        }
        return null;
    }


}
