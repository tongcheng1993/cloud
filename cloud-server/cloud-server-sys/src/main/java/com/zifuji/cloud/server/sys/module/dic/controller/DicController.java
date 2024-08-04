package com.zifuji.cloud.server.sys.module.dic.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.UpdateDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.UpdateDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.DicItemQo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.DicQo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicItemVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.zifuji.cloud.server.sys.module.dic.service.DicService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "数据字典控制器")
@RestController
@RequestMapping(value = "/dic")
@AllArgsConstructor
public class DicController {

	private DicService dicService;

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "新增数据字典类目")
	@PostMapping(value = "/addDic")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:addDic')")
	public Result<DicVo> addDic(@RequestBody @Valid AddDicMo addDicMo) {
		return Result.setObj(dicService.addDic(addDicMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除数据字典类目")
	@PostMapping(value = "/delDic")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:delDic')")
	public Result<Boolean> delDic(@RequestBody @Valid UpdateDicMo updateDicMo) {
		return Result.setObj(dicService.delDic(updateDicMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改数据字典类目")
	@PostMapping(value = "/updateDic")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:updateDic')")
	public Result<DicVo> resetDic(@RequestBody @Valid UpdateDicMo updateDicMo) {
		return Result.setObj(dicService.updateDic(updateDicMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询数据字典类目")
	@PostMapping(value = "/queryListDic")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:queryListDic')")
	public Result<List<DicVo>> queryListDic(@RequestBody @Valid DicQo dicQo) {
		return Result.setObj(dicService.queryListDic(dicQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页查询数据字典类目")
	@PostMapping(value = "/queryPageDic")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:queryPageDic')")
	public Result<IPage<DicVo>> queryPageDic(@RequestBody @Valid DicQo dicQo) {
		return Result.setObj(dicService.queryPageDic(dicQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "通过id查询")
	@PostMapping(value = "/getDicById")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:getDicById')")
	public Result<DicVo> getDicById(@RequestBody @Valid UpdateDicMo updateDicMo) {
		return Result.setObj(dicService.getDicById(updateDicMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "新增数据字典类别条目")
	@PostMapping(value = "/addDicItem")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:addDicItem')")
	public Result<DicItemVo> addDicItem(@RequestBody @Valid AddDicItemMo addDicItemMo) {
		return Result.setObj(dicService.addDicItem(addDicItemMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除数据字典类别条目")
	@PostMapping(value = "/delDicItem")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:delDicItem')")
	public Result<Boolean> delDicItem(@RequestBody @Valid UpdateDicItemMo updateDicItemMo) {
		return Result.setObj(dicService.delDicItem(updateDicItemMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改数据字典类别条目")
	@PostMapping(value = "/updateDicItem")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:updateDicItem')")
	public Result<DicItemVo> resetDicItem(@RequestBody @Valid UpdateDicItemMo updateDicItemMo) {
		return Result.setObj(dicService.updateDicItem(updateDicItemMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询数据字典类别条目")
	@PostMapping(value = "/queryListDicItem")
	@PreAuthorize(value = "hasAnyAuthority('sys:dic:queryListDicItem')")
	public Result<List<DicItemVo>> queryListDicItem(@RequestBody @Valid DicItemQo dicItemQo) {
		return Result.setObj(dicService.queryListDicItem(dicItemQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
	@ApiOperation(value = "获取全部字典")
	@PostMapping(value = "/getAllDicDetail")
	public Result<List<DicVo>> getAllDicDetail() {
		List<DicVo> result = dicService.getAllDicDetail();
		return Result.setObj(result);
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
	@ApiOperation(value = "获取单个字典")
	@PostMapping(value = "/getDicByCode")
	public Result<DicVo> getDicByCode(@RequestParam String dicCode) {
		return Result.setObj(dicService.getDicByCode(dicCode));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
	@ApiOperation(value = "获取code对应的value")
	@PostMapping(value = "/getValueByCode")
	public Result<String> getValueByCode(@RequestParam String dicCode, @RequestParam String itemCode) {
		return Result.setObj(dicService.getValueByCode(dicCode, itemCode));
	}

}
