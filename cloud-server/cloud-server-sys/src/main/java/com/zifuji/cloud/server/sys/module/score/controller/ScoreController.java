package com.zifuji.cloud.server.sys.module.score.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.score.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "score")
@RestController
@RequestMapping(value = "/score")
@AllArgsConstructor
public class ScoreController {


    private ScoreService scoreService;


    @ApiOperation(value = "")
    @GetMapping(value = "/registerScore")
    public Result<Boolean> registerScore(@RequestParam Long createById) {
        log.info(JSONObject.toJSONString(createById));
        Boolean result = scoreService.registerScore(createById);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }

}
