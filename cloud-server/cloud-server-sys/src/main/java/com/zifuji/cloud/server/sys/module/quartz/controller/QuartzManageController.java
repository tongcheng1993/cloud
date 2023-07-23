package com.zifuji.cloud.server.sys.module.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.quartz.mo.QuartzRecordControllerMo;
import com.zifuji.cloud.server.sys.module.quartz.qo.QuartzRecordPageQo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import com.zifuji.cloud.server.sys.module.quartz.vo.QuartzRecordControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(value = "定时器/管理")
@RestController
@RequestMapping(value = "/quartz/manage")
@AllArgsConstructor
public class QuartzManageController {

    private QuartzService quartzService;

    @ApiOperation(value = "增加一个定时器")
    @PostMapping(value = "/saveQuartzRecord")
    public Result<QuartzRecordControllerVo> saveQuartzRecord(@RequestBody @Valid QuartzRecordControllerMo quartzRecordMo) throws Exception {

        QuartzRecordControllerVo result = quartzService.saveQuartzRecord(quartzRecordMo);

        return new Result<QuartzRecordControllerVo>().setObj(result);
    }


    @ApiOperation(value = "")
    @GetMapping(value = "/delQuartzRecord")
    public Result<Boolean> delQuartzRecord(Long id) throws Exception {

        Boolean result = quartzService.delQuartzRecord(id);

        return new Result<Boolean>().setObj(result);
    }


    @ApiOperation(value = "分页查询定时器")
    @PostMapping(value = "/queryPageQuartzRecord")
    public Result<IPage<QuartzRecordControllerVo>> queryPageQuartzRecord(@RequestBody @Valid QuartzRecordPageQo quartzRecordPageQo) {

        IPage<QuartzRecordControllerVo> result = quartzService.queryPageQuartzRecord(quartzRecordPageQo);

        return new Result<IPage<QuartzRecordControllerVo>>().setObj(result);
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/syncQuartzList")
    public Result<Boolean> syncQuartzList() {

        Boolean result = quartzService.syncQuartzList();

        return new Result<Boolean>().setObj(result);
    }


}
