package com.zifuji.cloud.server.sys.module.area.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.db.area.entity.AreaEntity;
import com.zifuji.cloud.server.sys.module.area.controller.mo.AddAreaMo;
import com.zifuji.cloud.server.sys.module.area.controller.qo.AreaQo;
import com.zifuji.cloud.server.sys.module.area.controller.vo.AreaVo;
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
@Api(tags = "区域管理控制器")
@RestController
@RequestMapping(value = "/area")
@AllArgsConstructor
public class AreaController {


    private AreaService areaService;


    //增加
    @ApiOperation(value = "")
    @PostMapping(value = "/addArea")
    public Result<AreaVo> addArea(@RequestBody @Valid AddAreaMo addAreaMo) {

        return Result.setObj(null);
    }
    //删除

    //修改

    //查询
    @PostMapping(value = "/queryListArea")
    public Result<List<AreaVo>> queryListArea(@RequestBody @Valid AreaQo<AreaEntity> areaQo) {
        return Result.setObj(null);
    }

    @PostMapping(value = "/queryPageArea")
    public Result<IPage<AreaVo>> queryPageArea(@RequestBody @Valid AreaQo<AreaEntity> areaQo) {
        return Result.setObj(null);
    }





}
