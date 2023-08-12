package com.zifuji.cloud.server.sys.module.seq.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.seq.service.SeqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "流水号/应用")
@RestController
@RequestMapping(value = "/seq/apply")
@AllArgsConstructor
public class SeqApplyController {


    private SeqService seqService;


    @ApiOperation(value = "获取一个流水号的下一个号")
    @GetMapping(value = "/getNextSeq")
    public Result<String> getNextSeq(@RequestParam String code) {

        String result = seqService.getNextSeq(code);

        return Result.setObj(result);
    }







}
