package com.zifuji.cloud.server.sys.module.quartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.quartz.mo.QuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.qo.QuartzRecordPageQo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import com.zifuji.cloud.server.sys.module.quartz.vo.QuartzRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(value = "quartz/manage")
@RestController
@RequestMapping(value = "/quartz/manage")
@AllArgsConstructor
public class QuartzManageController {

    private QuartzService quartzService;

    @ApiOperation(value = "")
    @PostMapping(value = "/saveQuartzRecord")
    public Result<QuartzRecordVo> saveQuartzRecord(@RequestBody @Valid QuartzRecordMo quartzRecordMo) throws Exception {
        log.info(JSONObject.toJSONString(quartzRecordMo));
        QuartzRecordVo result = quartzService.saveQuartzRecord(quartzRecordMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<QuartzRecordVo>().setObj(result);
    }


    @ApiOperation(value = "")
    @GetMapping(value = "/delQuartzRecord")
    public Result<Boolean> delQuartzRecord(Long id) throws Exception {
        log.info(JSONObject.toJSONString(id));
        Boolean result = quartzService.delQuartzRecord(id);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }


    @ApiOperation(value = "")
    @PostMapping(value = "/queryPageQuartzRecord")
    public Result<IPage<QuartzRecordVo>> queryPageQuartzRecord(@RequestBody @Valid QuartzRecordPageQo quartzRecordPageQo) {
        log.info(JSONObject.toJSONString(quartzRecordPageQo));
        IPage<QuartzRecordVo> result = quartzService.queryPageQuartzRecord(quartzRecordPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<QuartzRecordVo>>().setObj(result);
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/syncQuartzList")
    public Result<Boolean> syncQuartzList() {
        log.info(JSONObject.toJSONString("空参数"));
        Boolean result = quartzService.syncQuartzList();
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }


}