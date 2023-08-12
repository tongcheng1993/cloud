package com.zifuji.cloud.server.sys.module.area.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.area.mo.SaveAreaControllerMo;
import com.zifuji.cloud.server.sys.module.area.qo.AreaPageQo;
import com.zifuji.cloud.server.sys.module.area.vo.AreaControllerVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.area.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "区域管理控制器")
@RestController
@RequestMapping(value = "/area")
@AllArgsConstructor
public class AreaController {


    private AreaService areaService;


    //增加
    @ApiOperation(value = "")
    @PostMapping(value = "/saveArea")
    public Result<String> saveArea(@RequestBody @Valid SaveAreaControllerMo saveAreaMo) {

        String result = areaService.saveArea(saveAreaMo);

        return Result.setObj(result);
    }
    //删除

    //修改

    //查询
    @PostMapping(value = "/queryPageArea")
    public Result<IPage<AreaControllerVo>> queryPageArea(@RequestBody @Valid AreaPageQo areaPageQo) {

        IPage<AreaControllerVo> result = areaService.queryPageArea(areaPageQo);

        return Result.setObj(result);
    }


    @PostMapping(value = "/queryListArea")
    public Result<List<AreaControllerVo>> queryListArea(@RequestBody @Valid AreaPageQo areaPageQo) {

        List<AreaControllerVo> result = areaService.queryListArea(areaPageQo);

        return Result.setObj(areaService.queryListArea(areaPageQo));
    }


}
