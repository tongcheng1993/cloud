package com.zifuji.cloud.server.business.module.score.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.score.qo.ScoreRecordPageQo;
import com.zifuji.cloud.server.business.module.score.service.ScoreService;
import com.zifuji.cloud.server.business.module.score.vo.ScoreAccountControllerVo;
import com.zifuji.cloud.server.business.module.score.vo.ScoreRecordControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(value = "积分/应用")
@RestController
@RequestMapping(value = "/score/apply")
@AllArgsConstructor
public class ScoreApplyController {


    private ScoreService scoreService;

    @ApiOperation(value = "获取个人账户积分信息")
    @GetMapping(value = "/getScoreAccountVoByMyself")
    public Result<ScoreAccountControllerVo> getScoreAccountVoByMyself() {
        ScoreAccountControllerVo result = scoreService.getScoreAccountVoByMyself();
        return Result.setObj(result);
    }

    @ApiOperation(value = "获取个人账户积分信息")
    @PostMapping(value = "/queryPageScoreRecord")
    public Result<IPage<ScoreRecordControllerVo>> queryPageScoreRecord(@RequestBody @Valid ScoreRecordPageQo scoreRecordPageQo) {
        IPage<ScoreRecordControllerVo> result = scoreService.queryPageScoreRecord(scoreRecordPageQo);
        return Result.setObj(result);
    }

}
