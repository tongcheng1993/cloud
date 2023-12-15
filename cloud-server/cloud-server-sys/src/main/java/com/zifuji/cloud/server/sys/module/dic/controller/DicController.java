package com.zifuji.cloud.server.sys.module.dic.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.dic.controller.mo.AddDicMo;
import com.zifuji.cloud.server.sys.module.dic.controller.qo.QueryPageDicQo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicItemVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.DicVO;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.GetAllDicDetailVo;
import com.zifuji.cloud.server.sys.module.dic.controller.vo.QueryPageDicVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "")
    @PostMapping(value = "/getAllDicDetail")
    @PreAuthorize(value = "hasAnyAuthority('sys:dic:getAllDicDetail')")
    public Result<List<GetAllDicDetailVo>> getAllDicDetail() {
        List<GetAllDicDetailVo> result = dicService.getAllDicDetail();
        return Result.setObj(result);
    }


    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "分页查询数据字典类目")
    @PostMapping(value = "/queryPageDic")
    @PreAuthorize(value = "hasAnyAuthority('sys:dic:queryPageDic')")
    public Result<IPage<QueryPageDicVo>> queryPageDic(@RequestBody @Valid QueryPageDicQo queryPageDicQo) {
        return Result.setObj(dicService.queryPageDic(queryPageDicQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "新增数据字典类目")
    @PostMapping(value = "/addDic")
    @PreAuthorize(value = "hasAnyAuthority('sys:dic:addDic')")
    public Result<DicVO> addDic(@RequestBody @Valid AddDicMo addDicMo) {
        DicVO result = dicService.addDic(addDicMo);
        return Result.setObj(result);
    }

    public Result<DicItemVo> addDicItem(){
        return null;
    }


}
