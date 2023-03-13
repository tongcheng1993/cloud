package com.zifuji.cloud.server.sys.module.useDb.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.useDb.qo.ExecuteSqlQo;
import com.zifuji.cloud.server.sys.module.useDb.service.UseDbService;
import com.zifuji.cloud.server.sys.module.useDb.vo.ExecuteSqlVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "数据库执行/管理")
@RestController
@RequestMapping(value = "/useDb/manage")
@AllArgsConstructor
public class UseDbManageController {

    private UseDbService useDbService;


    public Result<Boolean> testOpenDb(){
        return new Result<Boolean>().setObj(true);
    }


    @ApiOperation(value = "执行sql")
    @PostMapping(value = "/executeSql")
    @PreAuthorize(value = "hasAuthority('sys:useDb:executeSql')")
    public Result<ExecuteSqlVo> executeSql(ExecuteSqlQo executeSqlQo){
        log.info(JSONObject.toJSONString(executeSqlQo));
        ExecuteSqlVo result = useDbService.executeSql(executeSqlQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<ExecuteSqlVo>().setObj(result);
    }


}
