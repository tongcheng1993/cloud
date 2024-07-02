package com.zifuji.cloud.server.business.module.visit.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.visit.controller.qo.VisitGroupPageQo;
import com.zifuji.cloud.server.business.module.visit.service.VisitService;
import com.zifuji.cloud.server.business.module.visit.controller.vo.VisitGroupControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(tags = "出游应用控制器")
@RestController
@RequestMapping(value = "/visit")
@AllArgsConstructor
public class VisitController {

    private VisitService visitService;


    @ApiOperation(value = "")
    @PostMapping(value = "/queryPageVisitGroup")
    public Result<IPage<VisitGroupControllerVo>> queryPageVisitGroup(@RequestBody @Valid VisitGroupPageQo visitGroupPageQo){
        return Result.setObj(null);
    }
}
