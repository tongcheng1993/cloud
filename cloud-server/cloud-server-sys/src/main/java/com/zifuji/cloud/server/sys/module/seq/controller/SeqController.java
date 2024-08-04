package com.zifuji.cloud.server.sys.module.seq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.seq.controller.mo.SaveSeqControllerMo;
import com.zifuji.cloud.server.sys.module.seq.controller.qo.SeqPageQo;
import com.zifuji.cloud.server.sys.module.seq.controller.vo.SeqControllerVo;
import com.zifuji.cloud.server.sys.module.seq.service.SeqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "流水号控制器")
@RestController
@RequestMapping(value = "/seq")
@AllArgsConstructor
public class SeqController {

	private SeqService seqService;

	@ApiOperation(value = "获取一个流水号的下一个号")
	@GetMapping(value = "/getNextSeq")
	public Result<String> getNextSeq(@RequestParam String code) {
		return Result.setObj(seqService.getNextSeq(code));
	}

	@ApiOperation(value = "查询列表")
	@PostMapping(value = "/queryListSeq")
	public Result<List<SeqControllerVo>> queryListSeq(@RequestBody @Valid SeqPageQo seqPageQo) {
		return Result.setObj(seqService.queryListSeq(seqPageQo));
	}

	@ApiOperation(value = "分页查询列表")
	@PostMapping(value = "/queryPageSeq")
	public Result<IPage<SeqControllerVo>> queryPageSeq(@RequestBody @Valid SeqPageQo seqPageQo) {

		IPage<SeqControllerVo> result = seqService.queryPageSeq(seqPageQo);

		return Result.setObj(result);
	}

	@ApiOperation(value = "查询详情")
	@PostMapping(value = "/getSeqById")
	public Result<SeqControllerVo> getSeqById(@RequestParam Long id) {

		SeqControllerVo result = seqService.getSeqById(id);

		return Result.setObj(result);
	}

	@ApiOperation(value = "新建一个新的流水号")
	@PostMapping(value = "/saveSeq")
	public Result<SeqControllerVo> saveSeq(@RequestBody @Valid SaveSeqControllerMo saveSeqMo) {

		SeqControllerVo result = seqService.saveSeq(saveSeqMo);

		return Result.setObj(result);
	}
}
