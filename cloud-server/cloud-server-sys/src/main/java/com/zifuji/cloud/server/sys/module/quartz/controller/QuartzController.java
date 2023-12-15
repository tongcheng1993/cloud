package com.zifuji.cloud.server.sys.module.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.quartz.controller.mo.AddQuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.controller.mo.ResetQuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.controller.qo.QuartzRecordQo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import com.zifuji.cloud.server.sys.module.quartz.controller.vo.QuartzRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = "定时器控制器")
@RestController
@RequestMapping(value = "/quartz")
@AllArgsConstructor
public class QuartzController {

    private QuartzService quartzService;


    @ApiOperation(value = "同步定时器")
    @PostMapping(value = "/syncQuartzList")
    public Result<Boolean> syncQuartzList() {
        Boolean result = quartzService.syncQuartzList();
        return Result.setObj(result);
    }
















    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "增加一个定时器")
    @PostMapping(value = "/addQuartzRecord")
    @PreAuthorize(value = "hasAnyAuthority('sys:quartz:addQuartzRecord')")
    public Result<QuartzRecordVo> addQuartzRecord(@RequestBody @Valid AddQuartzRecordMo quartzRecordMo)  {
        QuartzRecordVo result = quartzService.addQuartzRecord(quartzRecordMo);
        return Result.setObj(result);
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "修改一个定时器")
    @PostMapping(value = "/resetQuartzRecord")
    @PreAuthorize(value = "hasAnyAuthority('sys:quartz:resetQuartzRecord')")
    public Result<QuartzRecordVo> resetQuartzRecord(@RequestBody @Valid ResetQuartzRecordMo resetQuartzRecordMo) {
        QuartzRecordVo result = quartzService.resetQuartzRecord(resetQuartzRecordMo);
        return Result.setObj(result);
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "删除一个定时器")
    @PostMapping(value = "/delQuartzRecord")
    @PreAuthorize(value = "hasAnyAuthority('sys:quartz:delQuartzRecord')")
    public Result<Boolean> delQuartzRecord(Long id)  {
        Boolean result = quartzService.delQuartzRecord(id);
        return Result.setObj(result);
    }

    @ApiOperation(value = "查询单个定时器")
    @PostMapping(value = "/queryOneQuartzRecord")
    public Result<QuartzRecordVo> queryOneQuartzRecord(@RequestBody @Valid QuartzRecordQo quartzRecordQo){
        return Result.setObj(null);
    }


    @ApiOperation(value = "分页查询定时器")
    @PostMapping(value = "/queryPageQuartzRecord")
    public Result<IPage<QuartzRecordVo>> queryPageQuartzRecord(@RequestBody @Valid QuartzRecordQo quartzRecordQo) {
        IPage<QuartzRecordVo> result = quartzService.queryPageQuartzRecord(quartzRecordQo);
        return Result.setObj(result);
    }




}
