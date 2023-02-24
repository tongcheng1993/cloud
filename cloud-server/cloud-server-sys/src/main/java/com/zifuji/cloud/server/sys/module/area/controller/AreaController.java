package com.zifuji.cloud.server.sys.module.area.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.area.mo.SaveAreaMo;
import com.zifuji.cloud.server.sys.module.area.qo.AreaPageQo;
import com.zifuji.cloud.server.sys.module.area.vo.AreaVo;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Result<String> saveArea(@RequestBody @Valid SaveAreaMo saveAreaMo) {
        log.info(JSONObject.toJSONString(saveAreaMo));
        String result = areaService.saveArea(saveAreaMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }
    //删除

    //修改

    //查询
    @PostMapping(value = "/queryPageArea")
    public Result<IPage<AreaVo>> queryPageArea(@RequestBody @Valid AreaPageQo areaPageQo) {
        log.info(JSONObject.toJSONString(areaPageQo));
        IPage<AreaVo> result = areaService.queryPageArea(areaPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<AreaVo>>().setObj(result);
    }


    @PostMapping(value = "/queryListArea")
    public Result<List<AreaVo>> queryListArea(@RequestBody @Valid AreaPageQo areaPageQo) {
        log.info(JSONObject.toJSONString(areaPageQo));
        List<AreaVo> result = areaService.queryListArea(areaPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<AreaVo>>().setObj(areaService.queryListArea(areaPageQo));
    }


}
