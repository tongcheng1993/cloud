package com.zifuji.cloud.server.sys.module.useDb.controller;

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
@Api(tags = "数据库查询控制器")
@RestController
@RequestMapping(value = "/useDb")
@AllArgsConstructor
public class UseDbController {

    private UseDbService useDbService;

    @ApiOperation(value = "测试能否打开连接")
    @PostMapping(value = "/testOpenDb")
    @PreAuthorize(value = "hasAnyAuthority('sys:useDb:testOpenDb')")
    public Result<Boolean> testOpenDb() {
        Boolean flag = useDbService.testOpenDb();
        return Result.setObj(flag);
    }


    @ApiOperation(value = "执行sql")
    @PostMapping(value = "/executeSql")
    @PreAuthorize(value = "hasAnyAuthority('sys:useDb:executeSql')")
    public Result<ExecuteSqlVo> executeSql(ExecuteSqlQo executeSqlQo) {
        ExecuteSqlVo result = useDbService.executeSql(executeSqlQo);
        return Result.setObj(result);
    }


}
