package com.zifuji.cloud.server.sys.module.seq.controller;

import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.seq.mo.SaveSeqMo;
import com.zifuji.cloud.server.sys.module.seq.service.SeqService;
import com.zifuji.cloud.server.sys.module.seq.vo.SeqVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebMenuVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.seq.qo.SeqPageQo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Api(value = "seq")
@RestController
@RequestMapping(value = "/seq")
@AllArgsConstructor
public class SeqController {

    private SeqService seqService;

    @ApiOperation(value = "查询列表")
    @PostMapping(value = "/queryListSeq")
    public Result<List<SeqVo>> queryListSeq(@RequestBody @Valid SeqPageQo seqPageQo) {
        log.info(JSONObject.toJSONString(seqPageQo));
        List<SeqVo> result = seqService.queryListSeq(seqPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<SeqVo>>().setObj(result);
    }

    @ApiOperation(value = "分页查询列表")
    @PostMapping(value = "/queryPageSeq")
    public Result<IPage<SeqVo>> queryPageSeq(@RequestBody @Valid SeqPageQo seqPageQo) {
        log.info(JSONObject.toJSONString(seqPageQo));
        IPage<SeqVo> result = seqService.queryPageSeq(seqPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<SeqVo>>().setObj(result);
    }

    @ApiOperation(value = "查询详情")
    @PostMapping(value = "/getSeqById")
    public Result<SeqVo> getSeqById(@RequestParam Long id) {
        log.info(JSONObject.toJSONString(id));
        SeqVo result = seqService.getSeqById(id);
        log.info(JSONObject.toJSONString(result));
        return new Result<SeqVo>().setObj(result);
    }


    @ApiOperation(value = "新建一个新的流水号")
    @PostMapping(value = "/saveSeq")
    public Result<SeqVo> saveSeq(@RequestBody @Valid SaveSeqMo saveSeqMo) {
        log.info(JSONObject.toJSONString(saveSeqMo));
        SeqVo result = seqService.saveSeq(saveSeqMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<SeqVo>().setObj(result);
    }


    @ApiOperation(value = "获取一个流水号的下一个号")
    @GetMapping(value = "/getNextSeq")
    public Result<String> getNextSeq(@RequestParam String code) {
        log.info(JSONObject.toJSONString(code));
        String result = seqService.getNextSeq(code);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


}