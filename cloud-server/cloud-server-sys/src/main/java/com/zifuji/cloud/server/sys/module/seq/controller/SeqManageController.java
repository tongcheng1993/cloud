package com.zifuji.cloud.server.sys.module.seq.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.seq.mo.SaveSeqControllerMo;
import com.zifuji.cloud.server.sys.module.seq.service.SeqService;
import com.zifuji.cloud.server.sys.module.seq.vo.SeqControllerVo;
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
@Api(value = "流水号/管理")
@RestController
@RequestMapping(value = "/seq")
@AllArgsConstructor
public class SeqManageController {

    private SeqService seqService;

    @ApiOperation(value = "查询列表")
    @PostMapping(value = "/queryListSeq")
    public Result<List<SeqControllerVo>> queryListSeq(@RequestBody @Valid SeqPageQo seqPageQo) {

        List<SeqControllerVo> result = seqService.queryListSeq(seqPageQo);

        return new Result<List<SeqControllerVo>>().setObj(result);
    }

    @ApiOperation(value = "分页查询列表")
    @PostMapping(value = "/queryPageSeq")
    public Result<IPage<SeqControllerVo>> queryPageSeq(@RequestBody @Valid SeqPageQo seqPageQo) {

        IPage<SeqControllerVo> result = seqService.queryPageSeq(seqPageQo);

        return new Result<IPage<SeqControllerVo>>().setObj(result);
    }

    @ApiOperation(value = "查询详情")
    @PostMapping(value = "/getSeqById")
    public Result<SeqControllerVo> getSeqById(@RequestParam Long id) {

        SeqControllerVo result = seqService.getSeqById(id);

        return new Result<SeqControllerVo>().setObj(result);
    }


    @ApiOperation(value = "新建一个新的流水号")
    @PostMapping(value = "/saveSeq")
    public Result<SeqControllerVo> saveSeq(@RequestBody @Valid SaveSeqControllerMo saveSeqMo) {

        SeqControllerVo result = seqService.saveSeq(saveSeqMo);

        return new Result<SeqControllerVo>().setObj(result);
    }





}
