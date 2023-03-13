package com.zifuji.cloud.server.sys.module.score.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.score.qo.ScoreRecordPageQo;
import com.zifuji.cloud.server.sys.module.score.service.ScoreService;
import com.zifuji.cloud.server.sys.module.score.vo.ScoreAccountVo;
import com.zifuji.cloud.server.sys.module.score.vo.ScoreRecordVo;
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
    public Result<ScoreAccountVo> getScoreAccountVoByMyself(){
        log.info(JSONObject.toJSONString("空参数"));
        ScoreAccountVo result = scoreService.getScoreAccountVoByMyself();
        log.info(JSONObject.toJSONString(result));
        return new Result<ScoreAccountVo>().setObj(result);
    }
    @ApiOperation(value = "获取个人账户积分信息")
    @PostMapping(value = "/queryPageScoreRecord")
    public Result<IPage<ScoreRecordVo>> queryPageScoreRecord(@RequestBody @Valid ScoreRecordPageQo scoreRecordPageQo){
        log.info(JSONObject.toJSONString(scoreRecordPageQo));
        IPage<ScoreRecordVo> result = scoreService.queryPageScoreRecord(scoreRecordPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<ScoreRecordVo>>().setObj(result);
    }

}
