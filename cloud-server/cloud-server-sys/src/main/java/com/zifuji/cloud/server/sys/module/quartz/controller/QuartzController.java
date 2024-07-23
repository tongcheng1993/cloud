package com.zifuji.cloud.server.sys.module.quartz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.quartz.controller.qo.QueryJobDetailQo;
import com.zifuji.cloud.server.sys.module.quartz.controller.vo.JobDetailVo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(tags = "定时器控制器")
@RestController
@RequestMapping(value = "/quartz")
@AllArgsConstructor
public class QuartzController {

    private QuartzService quartzService;

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询定时任务")
    @PostMapping(value = "/queryPageJobDetail")
    public Result<IPage<JobDetailVo>> queryPageJobDetail(@RequestBody @Valid QueryJobDetailQo queryJobDetailQo){
        return Result.setObj(quartzService.queryPageJobDetail(queryJobDetailQo));
    }







}
